package tporto.com.message;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tporto.com.message.helper.Base64Custom;
import tporto.com.message.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText e_nome, e_email, e_senha;
    private Button b_cadastrar;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        firebaseAuth = FirebaseAuth.getInstance();

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

                Log.i("create","OK");
                cadastrar();
            }
        });
    }

    private void cadastrar(){
        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String identificador = Base64Custom.converterBase64(usuario.getEmail());
                            usuario.setId(identificador);
                            usuario.salvar();
                            finish();
                        }else{
                            Toast.makeText(CadastroUsuarioActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
