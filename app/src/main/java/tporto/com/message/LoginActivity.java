package tporto.com.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import tporto.com.message.application.ConfiguracaoFirebase;
import tporto.com.message.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private Firebase firebase;
    private EditText e_email, e_senha;
    private Button b_logar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebase = ConfiguracaoFirebase.getFirebase();
        verificarUsuarioLogado();

        e_email = (EditText) findViewById(R.id.e_email);
        e_senha = (EditText) findViewById(R.id.e_senha);
        b_logar = (Button) findViewById(R.id.b_logar);

        b_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(e_email.getText().toString());
                usuario.setSenha(e_senha.getText().toString());
                validarLogin();
            }
        });
    }

    public void abrirCadastro(View v){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    private void validarLogin(){
        firebase.authWithPassword(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        abrirTelaPrincipal();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(LoginActivity.this,firebaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void verificarUsuarioLogado(){
        if(firebase.getAuth() != null){
            abrirTelaPrincipal();
        }
    }
}
