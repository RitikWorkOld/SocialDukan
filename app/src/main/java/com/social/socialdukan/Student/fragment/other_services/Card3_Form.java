package com.social.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Card3_Form extends AppCompatActivity {
TextInputEditText company_name,contact_no,emailid;

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

                            String notiid = FirebaseDatabase.getInstance().getReference().child("OtherServiceCard3")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().getKey();

                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "OtherServiceCard3" ).child( notiid);
                                                databaseReference.keepSynced( true );
                                                databaseReference.child( "companyname" ).setValue( company_name.getText().toString() );
                                                databaseReference.child( "contactno" ).setValue( contact_no.getText().toString() );
                                                databaseReference.child( "emailid" ).setValue( emailid.getText().toString() );
                            databaseReference.child( "id" ).setValue(notiid);
                            databaseReference.child( "read" ).setValue("no");
                                                databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );


                            Intent intent = new Intent( Card3_Form.this, Thanks_Activity.class );
                            company_name.setText( "" );
                            contact_no.setText( "" );
                            emailid.setText( "" );

                            startActivity( intent );




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
