package com.example.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detail_Submitted extends AppCompatActivity {
TextInputEditText name,contact,emailid,profilelink;
Button btn;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail__submitted );
        name=findViewById( R.id.name );
        contact=findViewById( R.id.contact_no );
        emailid=findViewById( R.id.emailid );
        profilelink=findViewById( R.id.profile_link );
        btn=findViewById( R.id.submit_btn );
        key = getIntent().getStringExtra("key");

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()) {
                    if (!contact.getText().toString().isEmpty()) {
                        if (!emailid.getText().toString().isEmpty()) {
                            if (!profilelink.getText().toString().isEmpty()) {


                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "InfluencerDetailSubmitted" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
                                databaseReference.keepSynced( true );
                                databaseReference.child( "name" ).setValue( name.getText().toString() );
                                databaseReference.child( "contact" ).setValue( contact.getText().toString() );
                                databaseReference.child( "emailID" ).setValue( emailid.getText().toString() );
                                databaseReference.child( "profilelink" ).setValue( "https://"+profilelink.getText().toString() );
                                databaseReference.child("key").setValue( key );
                                databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );


                                Toast.makeText( Detail_Submitted.this, "Done", Toast.LENGTH_SHORT ).show();
                                finish();

                            }
                                        else {
                                    Toast.makeText(Detail_Submitted.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                                }
                        }
                        else {
                            Toast.makeText(Detail_Submitted.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Detail_Submitted.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Detail_Submitted.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                }
            }
        } );

    }
}
