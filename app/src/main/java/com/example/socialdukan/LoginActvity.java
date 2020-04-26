package com.example.socialdukan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socialdukan.Employe.IntroActivity2;
import com.example.socialdukan.Student.Login_Register_Student.IntroActivity;

public class LoginActvity extends AppCompatActivity {
    private Button student_btn;
    private Button employe_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_actvity );
        student_btn=(Button) findViewById(R.id.stud_btn);
        employe_btn=(Button) findViewById(R.id.emp_btn);
        student_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==student_btn){
                    Intent intent=new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                }
            }
        } );
        employe_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==employe_btn){
                    Intent intent=new Intent(getApplicationContext(), IntroActivity2.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }
        } );
    }
}
