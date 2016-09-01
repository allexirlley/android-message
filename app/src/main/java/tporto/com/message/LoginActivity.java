package tporto.com.message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.Firebase;

import tporto.com.message.application.ConfiguracaoFirebase;

public class LoginActivity extends AppCompatActivity {

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebase = ConfiguracaoFirebase.getFirebase();
    }

    public void abrirCadastro(View v){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
