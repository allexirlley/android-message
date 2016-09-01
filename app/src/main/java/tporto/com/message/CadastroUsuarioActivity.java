package tporto.com.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import tporto.com.message.application.ConfiguracaoFirebase;
import tporto.com.message.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText e_nome, e_email, e_senha;
    private Button b_cadastrar;
    private Usuario usuario;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        e_nome  = (EditText) findViewById(R.id.e_usuario_nome);
        e_email = (EditText) findViewById(R.id.e_usuario_email);
        e_senha = (EditText) findViewById(R.id.e_usuario_senha);
        b_cadastrar = (Button) findViewById(R.id.b_usuario_cadastrar);

        b_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(e_email.getText().toString());
                usuario.setNome(e_nome.getText().toString());
                usuario.setSenha(e_senha.getText().toString());
                cadastrar();
            }
        });
    }

    private void cadastrar(){
        firebase = ConfiguracaoFirebase.getFirebase();
        firebase.createUser(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        usuario.setId(stringObjectMap.get("uid").toString());
                        usuario.salvar();
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(CadastroUsuarioActivity.this,firebaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
