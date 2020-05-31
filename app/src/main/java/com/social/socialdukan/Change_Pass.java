package com.social.socialdukan;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdukan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Change_Pass extends AppCompatActivity {

    EditText userEmail;
    private Toast backToast;
    private long backPressedTime;
    Button fsignin;
    FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_change__pass);

        userEmail=findViewById(R.id.email_et);
        fsignin=findViewById(R.id.send_btn);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        firebaseAuth=FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser==null){
                    //Toast.makeText(ForgotPass.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        fsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fsignin.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                String email = userEmail.getText().toString();
                if(email.isEmpty()){
                    fsignin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    userEmail.setError("Please enter email id");
                    userEmail.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    fsignin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    userEmail.setError(getString(R.string.input_error_email_invalid));
                    userEmail.requestFocus();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                            Toast.makeText(Change_Pass.this,"Please Check your Registered Email",Toast.LENGTH_LONG).show();

                                }
                                else{
                                    progressBar.setVisibility(View.GONE);
                                    fsignin.setVisibility(View.VISIBLE);
                                    Toast.makeText(Change_Pass.this,task.getException().getMessage()
                                            ,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finish();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();


        }
        backPressedTime = System.currentTimeMillis();

    }
}
