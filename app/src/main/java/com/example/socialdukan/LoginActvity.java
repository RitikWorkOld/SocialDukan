package com.example.socialdukan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.socialdukan.Employe.IntroActivity2;
import com.example.socialdukan.Employe.Login_Register_Employe.Login_Employe;
import com.example.socialdukan.Employe.Login_Register_Employe.Reg_Employe;
import com.example.socialdukan.Student.Login_Register_Student.IntroActivity;
import com.example.socialdukan.Student.Login_Register_Student.Login_Student;

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
                    Intent intent=new Intent(getApplicationContext(), Login_Student.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                }
            }
        } );
        employe_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==employe_btn){
                    Intent intent=new Intent(getApplicationContext(), Login_Employe.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }
        } );
    }
}
