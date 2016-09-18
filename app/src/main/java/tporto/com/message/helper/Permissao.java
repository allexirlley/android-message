package tporto.com.message.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tportopc on 29/08/16.
 */
public class Permissao {

    public static boolean validarPermissao(int requestCode, Activity activity, String[] permissoes){
        if(Build.VERSION.SDK_INT >= 23){
            List<String> lista = new ArrayList<String>();

            //verifica se já tem permissão
            for (String permissao : permissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao)
                    lista.add(permissao);
            }

            //caso a lista esteja vazia, não é necessário solicitar permissão
            if(lista.isEmpty())
                return true;

            String[] novasPermissoes = new String[lista.size()];
            lista.toArray(novasPermissoes);

            //solicita permissão
            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }

        return true;
    }

}
