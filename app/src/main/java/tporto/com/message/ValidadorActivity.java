package tporto.com.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import tporto.com.message.helper.Preferencias;

public class ValidadorActivity extends AppCompatActivity {

    private EditText edtCodigo;
    private Button btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        edtCodigo  = (EditText) findViewById(R.id.edtCodigoValidacao);
        btnValidar = (Button) findViewById(R.id.btn_validar);

        SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mascaraCodigo     = new MaskTextWatcher(edtCodigo,maskFormatter);

        edtCodigo.addTextChangedListener(mascaraCodigo);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar dados do usuário
                Preferencias preferencias      = new Preferencias(ValidadorActivity.this);
                HashMap<String,String> usuario = preferencias.getUsuario();

                String tokenGerado   = usuario.get("token");
                String tokenDigitado = edtCodigo.getText().toString();

                if (tokenDigitado.equals(tokenGerado)){
                    Toast.makeText(ValidadorActivity.this,"Token Validado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ValidadorActivity.this,"Token Inválido",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
