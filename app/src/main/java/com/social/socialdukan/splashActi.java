package com.social.socialdukan;


import static android.app.Application.getProcessName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
;
import com.example.socialdukan.R;
import com.google.firebase.FirebaseApp;
import com.social.socialdukan.Student.Login_Register_Student.Login_Student;
import com.social.socialdukan.Student.Login_Register_Student.Login_Student;


import java.util.Timer;
import java.util.TimerTask;

public class splashActi extends AppCompatActivity {

Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );


        // FirebaseMessaging.getInstance().subscribeToTopic(getString( R.string.default_notification_channel_id));


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActi.this, Login_Student.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }
}
