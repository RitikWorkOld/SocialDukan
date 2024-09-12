package com.social.socialdukan.Student.Login_Register_Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import for logging
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.social.socialdukan.Student.Login_Register_Student.Utils.Save;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.example.socialdukan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {

    private static final String TAG = "VerificationActivity"; // Tag for logging

    private FirebaseAuth mFirebaseAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken token;

    ImageView crossiv;
    Button verify_btn, login_btn;
    EditText otp_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        initializeUI();
        initializeFirebase();

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String pwd = intent.getStringExtra("password");
        final String fname = intent.getStringExtra("name");
        final String number = intent.getStringExtra("number");
        final String code1 = intent.getStringExtra("code");

        // Log phone number before sending OTP
        Log.d(TAG, "Phone number received: " + number);
String cleanN = "+91" + number;
        // Send OTP to the user's phone number
        sendVerificationCode(cleanN);

        verify_btn.setOnClickListener(v -> {
            String code = otp_et.getText().toString().trim();
            if (code.isEmpty() || code.length() < 6) {
                otp_et.setError("Enter valid OTP");
                otp_et.requestFocus();
                return;
            }
            verifyVerificationCode(code);  // Verifying OTP
        });

        crossiv.setOnClickListener(v -> finish());
    }

    private void initializeUI() {
        crossiv = findViewById(R.id.cross_btn_rf);
        verify_btn = findViewById(R.id.verify_btn);
        login_btn = findViewById(R.id.login_btn);
        otp_et = findViewById(R.id.otp_et);  // Assuming you have an EditText for OTP
    }

    private void initializeFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    // Method to send the OTP to the phone number
    private void sendVerificationCode(String number) {
        Log.d(TAG, "Sending verification code to: " + number);  // Log before sending OTP

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mFirebaseAuth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // Callback to handle OTP events
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                Log.d(TAG, "OTP received automatically: " + code);  // Log received OTP
                otp_et.setText(code);  // Automatically filling OTP
                verifyVerificationCode(code);  // Verifying OTP
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.e(TAG, "Verification failed: " + e.getMessage());  // Log error message
            Toast.makeText(Verification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(verificationId, forceResendingToken);
            Log.d(TAG, "Code sent, verificationId: " + verificationId);  // Log verificationId
            mVerificationId = verificationId;
            token = forceResendingToken;
        }
    };

    // Method to verify the OTP entered by the user
    private void verifyVerificationCode(String code) {
        Log.d(TAG, "Verifying OTP: " + code);  // Log OTP being verified
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);  // Sign in after OTP verification
    }

    // Method to sign in using OTP
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(Verification.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "OTP verification successful.");  // Log success

                    Intent intent = getIntent();
                    final String email = intent.getStringExtra("email");
                    final String pwd = intent.getStringExtra("password");
                    final String fname = intent.getStringExtra("name");
                    final String number = intent.getStringExtra("number");
                    final String code1 = intent.getStringExtra("code");

                    // Register user after OTP verification
                    registerUser(email, pwd, fname, code1, number);
                } else {
                    Log.e(TAG, "OTP verification failed: " + task.getException());  // Log failure
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(Verification.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Verification.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Method to register the user after OTP verification and send email verification
    private void registerUser(String email, String pwd, String fname, String code, String number) {
        Log.d(TAG, "Registering user with email: " + email);  // Log user registration details

        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                        if (user != null) {
                            // Send email verification
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Verification email sent.");  // Log email sent
                                        saveUserToDatabase(fname, email, number, code, pwd);
                                        Toast.makeText(Verification.this, "Verification email sent. Please verify your email before logging in.", Toast.LENGTH_LONG).show();
                                        showLoginUI();
                                    } else {
                                        Log.e(TAG, "Failed to send verification email: " + task.getException().getMessage());  // Log failure
                                        Toast.makeText(Verification.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    } else {
                        showErrorToast(task.getException().getMessage());
                        finish();
                    }
                });
    }

    private void saveUserToDatabase(String fname, String email, String number, String code, String pwd) {
        String uid = FirebaseAuth.getInstance().getUid();
        User user = new User(fname, email, number, uid, pwd, null, "no", code);

        Log.d(TAG, "Saving user to database with UID: " + uid);  // Log saving user to database

        FirebaseDatabase.getInstance().getReference("Users")
                .child(uid)
                .setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User saved successfully.");  // Log success
                        Save.save(getApplicationContext(), "session", "false");
                    } else {
                        Log.e(TAG, "Failed to save user data: " + task.getException());  // Log failure
                        showErrorToast("Failed to save user data.");
                    }
                });
    }

    private void showLoginUI() {
        verify_btn.setVisibility(View.GONE);
        login_btn.setVisibility(View.VISIBLE);
        login_btn.setOnClickListener(v -> {
            startActivity(new Intent(Verification.this, Login_Student.class));
            finish();
        });
    }

    private void showErrorToast(String message) {
        Toast.makeText(Verification.this, message, Toast.LENGTH_LONG).show();
    }
}
