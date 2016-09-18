package tporto.com.message.application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tportopc on 31/08/16.
 */
public final class ConfiguracaoFirebase {

    private static DatabaseReference firebase;

    public static DatabaseReference getFirebase(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }
}
