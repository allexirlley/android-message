package tporto.com.message.application;

import com.firebase.client.Firebase;

/**
 * Created by tportopc on 31/08/16.
 */
public final class ConfiguracaoFirebase {

    private static Firebase firebase;
    private static final String URL = "https://tportomessage.firebaseio.com";

    public static Firebase getFirebase(){
        if(firebase == null){
            firebase = new Firebase(URL);
        }
        return firebase;
    }
}
