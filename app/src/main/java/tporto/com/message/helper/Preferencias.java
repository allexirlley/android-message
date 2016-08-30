package tporto.com.message.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by thiago on 24/08/16.
 */
public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "messages.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private String CHAVE_NOME     = "nome";
    private String CHAVE_TELEFONE = "telefone";
    private String CHAVE_TOKEN    = "token";

    public Preferencias(Context contextPrm){
        this.context     = contextPrm;
        this.preferences = context.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor           = preferences.edit();
    }

    public void salvarUsuario(String nome,String telefone,String token){
        editor.putString(CHAVE_NOME,nome);
        editor.putString(CHAVE_TELEFONE,telefone);
        editor.putString(CHAVE_TOKEN,token);
        editor.commit();
    }

    public HashMap<String,String> getUsuario(){
        HashMap<String,String> dadosUsuario = new HashMap<>();
        dadosUsuario.put(CHAVE_NOME,preferences.getString(CHAVE_NOME,null));
        dadosUsuario.put(CHAVE_TELEFONE,preferences.getString(CHAVE_TELEFONE,null));
        dadosUsuario.put(CHAVE_TOKEN,preferences.getString(CHAVE_TOKEN,null));

        return dadosUsuario;
    }
}
