package tporto.com.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone, codpais, codigo, ddd, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome     = (EditText) findViewById(R.id.edtNome);
        telefone = (EditText) findViewById(R.id.edtNumero);
        codpais  = (EditText) findViewById(R.id.edtCodPais);
        codigo   = (EditText) findViewById(R.id.edtCodigo);
        ddd      = (EditText) findViewById(R.id.edtDDD);

        SimpleMaskFormatter sfCodpais  = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter sfDDD      = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter sfTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher mtCodpais      = new MaskTextWatcher(codpais,sfCodpais);
        MaskTextWatcher mtDDD          = new MaskTextWatcher(ddd,sfDDD);
        MaskTextWatcher mtTelefone     = new MaskTextWatcher(telefone,sfTelefone);

        codpais.addTextChangedListener(mtCodpais);
        ddd.addTextChangedListener(mtDDD);
        telefone.addTextChangedListener(mtTelefone);
    }
}
