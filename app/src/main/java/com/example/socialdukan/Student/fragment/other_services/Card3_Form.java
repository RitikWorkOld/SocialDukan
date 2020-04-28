package com.example.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Card3_Form extends AppCompatActivity {
EditText company_name,contact_no,emailid;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card3__form );
        company_name=findViewById( R.id.company_name );
        contact_no=findViewById( R.id.contact_no );
        emailid=findViewById( R.id.emailid );
        btn=findViewById( R.id.submit_btn );




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!company_name.getText().toString().isEmpty()) {
                    if (!contact_no.getText().toString().isEmpty()) {
                        if (!emailid.getText().toString().isEmpty()) {



                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard3" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                databaseReference.keepSynced( true );
                                                databaseReference.child( "companyname" ).setValue( company_name.getText().toString() );
                                                databaseReference.child( "contactno" ).setValue( contact_no.getText().toString() );
                                                databaseReference.child( "emailid" ).setValue( emailid.getText().toString() );

                                                databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );



                                                Toast.makeText( Card3_Form.this, "Done", Toast.LENGTH_SHORT ).show();
                                                finish();



                        }
                        else {
                            Toast.makeText(Card3_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Card3_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Card3_Form.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}