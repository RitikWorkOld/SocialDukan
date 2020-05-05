package com.example.socialdukan.Employe.Login_Register_Employe;

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
import android.widget.ImageView;
import android.widget.Toast;


import com.example.socialdukan.Employe.IntroActivity2;
import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.Student.Chat_bot.feature.MainActivity;
import com.example.socialdukan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Employe extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener {
    Button btnSignUp;
    ImageButton go;
    EditText emailId, password;
    FirebaseAuth mFirebaseAuth;
    String pstatus;
    String ostatus;
    ImageView chatbot;


    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs1";
    private static final String KEY_REMEMBER = "remember1";
    private static final String KEY_USERNAME = "username1";
    private static final String KEY_PASS = "password1";
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login__employe );
        chatbot=findViewById( R.id.chatbot );
        chatbot.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Employe.this, MainActivity.class);
                startActivity(intent);
            }
        } );
        btnSignUp=findViewById(R.id.signup);
        go=findViewById( R.id.go1 );


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
                    Toast.makeText( Login_Employe.this, "Fields Are Empty!", Toast.LENGTH_SHORT ).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    //check this runs
                    Log.d( "LOOP 1", "status: login " );//ye lga rhndo

                    mFirebaseAuth.signInWithEmailAndPassword( email, pwd ).addOnCompleteListener( Login_Employe.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                //progressBars.setVisibility(View.GONE);

                                Toast.makeText( Login_Employe.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT ).show();
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

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Employe");
                                databaseReference.keepSynced(true);
                                databaseReference.orderByChild("eid").equalTo(mFirebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                            Employe user = dataSnapshot1.getValue(Employe.class);

                                            pstatus = user.profilestatus;
                                            ostatus=user.officialstatus;

                                            Log.d("HEL**************","**************************************   "+pstatus);

                                            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();

                                            if ( firebaseUser.isEmailVerified())
                                            {
                                                if (pstatus.equals("yes") && ostatus.equals( "yes" ) ){
                                                    Log.d("HEL","msg= yha agya");
                                                   // Toast.makeText(Login_Employe.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Login_Employe.this, Dashboard_emp.class);
                                                  startActivity(intent);
                                                    finish();
                                                }
                                               /* else if(pstatus.equals( "yes" ) && ostatus.equals("no")){
                                                    Toast.makeText(Login_Employe.this, "Your account is in verification, Please Wait", Toast.LENGTH_SHORT).show();


                                                }*/
                                                else {
                                                    Log.d("HEL","msg= yha agya");
                                                   // Toast.makeText(Login_Employe.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Login_Employe.this, Employe_detail.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                            else
                                            {
                                                // email is not verified, so just prompt the message to the user and restart this activity.
                                                // NOTE: don't forget to log out the user.
                                                FirebaseAuth.getInstance().signOut();
                                                Toast.makeText(Login_Employe.this, "Email Not Verified", Toast.LENGTH_SHORT).show();
                                                //restart this activity
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
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
                    Intent intent = new Intent( Login_Employe.this, IntroActivity2.class );
                    startActivity( intent );
                    overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                    finish();
                }
            }
        } );

    }


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
