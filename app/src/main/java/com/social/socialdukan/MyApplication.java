package com.social.socialdukan;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "Before Firebase initialization");
        FirebaseApp.initializeApp(this);
        Log.d("MyApplication", "After Firebase initialization");
    }
}
