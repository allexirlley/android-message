package tporto.com.message.helper;

import android.util.Base64;

/**
 * Created by thiago on 11/09/16.
 */
public class Base64Custom {

    public static String converterBase64(String texto){
        String textoConvertido = Base64.encodeToString(texto.getBytes(),Base64.DEFAULT);
        return textoConvertido;
    }

    public static String decodificarBase64(String textoCodificado){
        byte[] byteDecodificado = Base64.decode(textoCodificado,Base64.DEFAULT);
        return new String(byteDecodificado);
    }
}
