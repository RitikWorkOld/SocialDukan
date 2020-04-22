package com.example.socialdukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Student extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener {
    Button btnSignUp;
    ImageButton go;
    EditText emailId, password;
    FirebaseAuth mFirebaseAuth;


    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login__student );

        btnSignUp=findViewById(R.id.signup);
        go=findViewById( R.id.go1 );

        btnSignUp = findViewById( R.id.signup );
        go = findViewById( R.id.go1 );

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);
        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);




        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById( R.id.Lemail );
        password = findViewById( R.id.Lpass );
        emailId.setText( sharedPreferences.getString( KEY_USERNAME, "" ) );
        password.setText( sharedPreferences.getString( KEY_PASS, "" ) );

        emailId.addTextChangedListener( this );
        password.addTextChangedListener( this );
        rem_userpass.setOnCheckedChangeListener( this );

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser==null){
                    // Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();*******************************************************************
                }
            }
        };


        go.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    // progressBars.setVisibility(View.GONE);
                    emailId.setError( "Please enter email id" );
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    //progressBars.setVisibility(View.GONE);
                    password.setError( "Please enter your password" );
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    //   progressBars.setVisibility(View.GONE);
                    Toast.makeText( Login_Student.this, "Fields Are Empty!", Toast.LENGTH_SHORT ).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    //check this runs
                    Log.d( "LOOP 1", "status: login " );//ye lga rhndo

                    mFirebaseAuth.signInWithEmailAndPassword( email, pwd ).addOnCompleteListener( Login_Student.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                //progressBars.setVisibility(View.GONE);

                                Toast.makeText( Login_Student.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT ).show();
                                // Intent intent = new Intent(LoginActivity.this,Login_Failed.class);
                                //  startActivity(intent);
                            } else {
                                // progressBars.setVisibility(View.GONE);
                                Log.d( ">> NOTWORKING 1", "onComplete: + COME IN LOOP " );
                                ////yha bhi aaya run statement...ok
                                //Toast.makeText( Login_Student.this, "Welcome", Toast.LENGTH_SHORT ).show();
                                //saving session
                                // Save.save(getApplicationContext(),"session","true");
                                // Intent intToHome = new Intent(getApplicationContext(),Dashboard.class);//not working TEAM.
                                //   startActivity(intToHome);
                                // finish();

                                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();

                                if ( firebaseUser.isEmailVerified())
                                {
                                    // user is verified, so you can finish this activity or send user to activity which you want.
                                    // finish();
                                    Log.d("HEL","msg= yha agya");
                                    Toast.makeText(Login_Student.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login_Student.this, Studentdetail.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else
                                {
                                    // email is not verified, so just prompt the message to the user and restart this activity.
                                    // NOTE: don't forget to log out the user.
                                    FirebaseAuth.getInstance().signOut();
                                    Toast.makeText(Login_Student.this, "Email Not Verified", Toast.LENGTH_SHORT).show();
                                    //restart this activity
                                }
                            }
                        }
                    } );
            }
           }
        } );



        btnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnSignUp) {
                    Intent intent = new Intent( Login_Student.this, Reg_Student.class );
                    startActivity( intent );
                    overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                    finish();
                }
            }
        } );

    }

   /*private boolean checkIfEmailVerified()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        Log.d( ">> NOTWORKING 1", "onComplete: + COME IN LOOP " );

        if ( firebaseUser.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            // finish();
            Log.d("HEL","msg= yha agya");
            Toast.makeText(Login_Student.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Login_Student.this, "Email Not Verified", Toast.LENGTH_SHORT).show();
            //restart this activity
            return false;
        }
    }*/

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();

    }

    private void managePrefs() {
        if (rem_userpass.isChecked()) {
            editor.putString( KEY_USERNAME, emailId.getText().toString().trim() );
            editor.putString( KEY_PASS, password.getText().toString().trim() );
            editor.putBoolean( KEY_REMEMBER, true );
            editor.apply();
        } else {
            editor.putBoolean( KEY_REMEMBER, false );
            editor.remove( KEY_PASS );//editor.putString(KEY_PASS,"");
            editor.remove( KEY_USERNAME );//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
