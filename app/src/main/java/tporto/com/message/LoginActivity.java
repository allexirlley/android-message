package tporto.com.message;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Random;

import tporto.com.message.helper.Permissao;
import tporto.com.message.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone, codpais, codigo, ddd, nome;
    private Button btnCadastrar;
    private String[] permissoes = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Permissao.validarPermissao(1,this,permissoes);

        nome     = (EditText) findViewById(R.id.edtNome);
        telefone = (EditText) findViewById(R.id.edtNumero);
        codpais  = (EditText) findViewById(R.id.edtCodPais);
        codigo   = (EditText) findViewById(R.id.edtCodigo);
        ddd      = (EditText) findViewById(R.id.edtDDD);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        SimpleMaskFormatter sfCodpais  = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter sfDDD      = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter sfTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher mtCodpais      = new MaskTextWatcher(codpais,sfCodpais);
        MaskTextWatcher mtDDD          = new MaskTextWatcher(ddd,sfDDD);
        MaskTextWatcher mtTelefone     = new MaskTextWatcher(telefone,sfTelefone);

        codpais.addTextChangedListener(mtCodpais);
        ddd.addTextChangedListener(mtDDD);
        telefone.addTextChangedListener(mtTelefone);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario          = nome.getText().toString();
                String telefoneCompleto = codpais.getText().toString()+ddd.getText().toString()+telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+","").replace("-","");

                //Gerar token
                Random random = new Random();
                int numero    = random.nextInt(9999-1000)+1000;
                String token  = String.valueOf(numero);
                String msgEnvio = "Message Código de confirmação: "+token;

                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuario(usuario,telefoneSemFormatacao,token);

                telefoneSemFormatacao = "5554";
                boolean enviado = envioSMS("+"+telefoneSemFormatacao,msgEnvio);
            }
        });
    }

    private boolean envioSMS(String telefone, String msg){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone,null,msg,null,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
