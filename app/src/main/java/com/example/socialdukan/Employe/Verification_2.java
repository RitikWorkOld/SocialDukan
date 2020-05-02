package com.example.socialdukan.Employe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.Student.Login_Register_Student.Studentdetail;
import com.example.socialdukan.Student.Miscellaneous.User;
import com.example.socialdukan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

public class Verification_2 extends AppCompatActivity {

    private String mVerificationId;
    PhoneAuthProvider.ForceResendingToken token;
    private FirebaseAuth mAuth;




    private FirebaseAuth mFirebaseAuth;
    int code=91;



    Button verify_btn,login_btn;
    ImageView user_img;
    EditText otp;
    TextView email_txt,chk_email,enterotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_verification );

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();




        verify_btn=findViewById( R.id.verify_btn );
        otp=findViewById( R.id.otp_et );
        email_txt=findViewById( R.id.textemail );
        chk_email=findViewById( R.id.chk_email );
        enterotp=findViewById( R.id.title_001 );
        login_btn=findViewById( R.id.login_btn );


        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String pwd = intent.getStringExtra("password");
        final String fname = intent.getStringExtra("name");
        final String number = intent.getStringExtra("number");


        final String mobile = "+"+code+number;
        sendVerificationCode(mobile);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    otp.setError("Enter valid code");
                    otp.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });



        /*verify_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==verify_btn){
                    otp.setVisibility( View.GONE );
                    verify_btn.setVisibility( View.GONE );
                    enterotp.setVisibility( View.GONE );
                    chk_email.setVisibility( View.VISIBLE );
                    email_txt.setVisibility( View.VISIBLE );
                }
            }
        } );*/
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                120,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otp.setText(code);

                //verifying the code
                verifyVerificationCode(code);
            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verification_2.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
            token=forceResendingToken;

        }
    };
    private void verifyVerificationCode(String code) {
        //creating the credential
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            mAuth.signOut();
            mFirebaseAuth.signOut();
            //signing the user

            signInWithPhoneAuthCredential(credential);
        }catch (Exception e){ //ss
            Toast toast = Toast.makeText(getApplicationContext(), "Error,Please try again lator", Toast.LENGTH_SHORT);
            toast.setGravity( Gravity.CENTER,0,0);
            toast.show();
        }

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(Verification_2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    //verification successful we will start the profile activity


                    //EMAIL BHEJNE KA CODE YHA AYEGA SHYAAD

                  //  detailuser();
                    registeruser();

                } else {

                    //verification unsuccessful.. display an error message

                    String message = "Somthing is wrong, we will fix it soon...";

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered...";
                    }

                    Toast.makeText(Verification_2.this,message, Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    private void registeruser() {

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String pwd = intent.getStringExtra("password");
        final String fname = intent.getStringExtra("name");

        final String number = intent.getStringExtra("number");


        //  progressBar.setVisibility(View.VISIBLE);
        verify_btn.setVisibility(View.GONE);
        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            otp.setVisibility( View.GONE );
                            verify_btn.setVisibility( View.GONE );
                            enterotp.setVisibility( View.GONE );



                            chk_email.setVisibility( View.VISIBLE );
                            email_txt.setVisibility( View.VISIBLE );
                            login_btn.setVisibility( View.VISIBLE );
                            login_btn.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Verification_2.this, Employe_detail.class);
                                    startActivity( intent );

                                }
                            } );
                        }
                    });

                    //final String refrelid = endvr.concat(number);
                    String uid = FirebaseAuth.getInstance().getUid();
                    Employe employe=new Employe(fname,email,number,uid,pwd,null,"no");

                    FirebaseDatabase.getInstance().getReference("Employe")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(employe).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // progressBar.setVisibility(View.GONE);
                            //progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {




                                //saving session
                                //   Save.save(getApplicationContext(),"session","false");


                                // Toast.makeText(Verification.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                // Intent intent = new Intent(Verification.this,Reg_Sucess.class);
                                //intent.putExtra("referid",refrelid);
                                //    startActivity(intent);
                                // finish();
                            }
                            else {
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                    // Intent intent = new Intent(RequestOtp.this,Reg_Fail.class);
                                    //  startActivity(intent);
                                    //  finish();
                                }

                            }
                        }
                    });

                }
                else {
                    //progressBar.setVisibility(View.GONE);
                    //Toast.makeText(RegAct.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    //   Intent intent = new Intent(RequestOtp.this,Reg_Fail.class);
                    // startActivity(intent);
                    finish();
                }
            }
        });
    }


}