package tporto.com.message.application;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by tportopc on 31/08/16.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
