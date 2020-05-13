package com.example.socialdukan.Student.Login_Register_Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Chat_bot.feature.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Reg_Student extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn;
    ImageButton go;
    private EditText emailId,password,number1,fname1;
    FirebaseAuth mFirebaseAuth;
    ImageView chatbot;
    private ProgressBar progressBars;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reg__student );
        mFirebaseAuth = FirebaseAuth.getInstance();
        progressBars = findViewById(R.id.progressBar2);
        progressBars.setVisibility(View.GONE);

        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        fname1 = findViewById(R.id.fname);
        number1 = findViewById(R.id.cnumber);
        chatbot=findViewById( R.id.chatbot );

        chatbot.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reg_Student.this, MainActivity.class);
                startActivity(intent);
            }
        } );

        findViewById( R.id.go ).setOnClickListener( this );



        btnSignIn=findViewById(R.id.btn_signin);

        btnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==btnSignIn){
                    Intent intent = new Intent(Reg_Student.this, Login_Student.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            }
        } );








      /*  go.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==go){
                    Intent intent = new Intent(Reg_Student.this, Verification.class);
                    startActivity(intent);
                }
            }
        } );*/
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (mFirebaseAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.go:
                progressBars.setVisibility(View.VISIBLE);
                findViewById( R.id.go ).setVisibility( View.VISIBLE );

                boolean valid = validateUser();

                //  Log.i("Hello", "working");
                if (valid) {

                  /*  final String number=number1.getText().toString().trim();
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Users");
                    dbref.keepSynced(true);
                    dbref.orderByChild("contactn").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){
                              //  progressBar.setVisibility(View.GONE);
                                findViewById(R.id.go).setVisibility(View.VISIBLE);
                                Toast.makeText(Reg_Student.this,"User on this phone Number Already Exists",Toast.LENGTH_SHORT).show();
                            }
                            else {*/
                    final String email = emailId.getText().toString().trim();
                    final String pwd = password.getText().toString().trim();
                    final String fname = fname1.getText().toString().trim();

                    final String number = number1.getText().toString().trim();
                    progressBars.setVisibility(View.GONE);
                    Intent intent = new Intent( Reg_Student.this, Verification.class );
                    intent.putExtra( "name", fname );
                    intent.putExtra( "email", email );
                    intent.putExtra( "password", pwd );

                    intent.putExtra( "number", number );

                    startActivity( intent );

                    //  progressBar.setVisibility(View.GONE);
                    findViewById( R.id.go ).setVisibility( View.VISIBLE );
                    //Toast.makeText(RegAct.this,"NO user found",Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBars.setVisibility(View.GONE);

                }
                break;
        }

                      /*  @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/
    }
                /*else {
                   // progressBar.setVisibility(View.GONE);
                    findViewById(R.id.go).setVisibility(View.VISIBLE);
                }*/
                //break;


    private boolean validateUser() {
        final String email=emailId.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        final String fname=fname1.getText().toString().trim();

        final String number=number1.getText().toString().trim();


        if(fname.isEmpty()){
            fname1.setError(getString(R.string.input_error_name));
            fname1.requestFocus();
            return false;
        }
        else if(email.isEmpty()){
            emailId.setError(getString(R.string.input_error_email));
            emailId.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.setError(getString(R.string.input_error_email_invalid));
            emailId.requestFocus();
            return false;
        }
        else if(pwd.isEmpty()){
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return false;
        }
        else if (pwd.length() < 6 ) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(pwd).matches()){

            password.setError("1 Digit? \n 1 LowerCase? \n 1 UpperCase? \n 1 Special Character? \n atleast 6 character?");
            password.requestFocus();
            return false;
        }



        else if(number.isEmpty()){
            number1.setError("Please Enter Your Number");
            number1.requestFocus();
            return false;
        }

        else if (number.length() != 10) {
            number1.setError(getString(R.string.input_error_phone_invalid));
            number1.requestFocus();
            return false;
        }


        else {
            return true;
        }
    }

}

