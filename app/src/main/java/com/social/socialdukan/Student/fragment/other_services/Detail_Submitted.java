package com.social.socialdukan.Student.fragment.other_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Internship.ApplyIntern;
import com.social.socialdukan.Student.fragment.Internship.InternDetail;
import com.social.socialdukan.Student.fragment.other_services.model.card_model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Detail_Submitted extends AppCompatActivity {
TextInputEditText name,contact,emailid,profilelink,follower;
Button btn;

    String id;
    ImageView cross;
    String userid,panel_userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail__submitted );
        name=findViewById( R.id.name );
        cross=findViewById( R.id.cross_btn_rf );
        contact=findViewById( R.id.contact_no );
        emailid=findViewById( R.id.emailid );
        profilelink=findViewById( R.id.profile_link );
        follower=findViewById( R.id.follower );
        btn=findViewById( R.id.submit_btn );
        id = getIntent().getStringExtra("id");
        userid = getIntent().getStringExtra("userid");
        panel_userid = getIntent().getStringExtra("panel_userid");
        cross.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()) {
                    if (!contact.getText().toString().isEmpty()) {
                        if (!emailid.getText().toString().isEmpty()) {
                            if (!profilelink.getText().toString().isEmpty()) {
                                if(!follower.getText().toString().isEmpty()){




                                String notiid = FirebaseDatabase.getInstance().getReference().child("InfluencerDetailSubmitted")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().getKey();

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "InfluencerDetailSubmitted" ).child( id );
                                databaseReference.keepSynced( true );
                                databaseReference.child( "name" ).setValue( name.getText().toString() );
                                databaseReference.child( "contact" ).setValue( contact.getText().toString() );
                                databaseReference.child( "emailid" ).setValue( emailid.getText().toString() );
                                databaseReference.child( "profilelink" ).setValue( "https://"+profilelink.getText().toString() );
                                databaseReference.child("influencerid").setValue( id );
                                databaseReference.child("id").setValue( notiid );
                                    databaseReference.child( "follower" ).setValue( follower.getText().toString());
                                databaseReference.child( "userid" ).setValue( FirebaseAuth.getInstance().getCurrentUser().getUid() );
                                databaseReference.child( "id_status" ).setValue( panel_userid);

                                Toast.makeText( Detail_Submitted.this, "Applied", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), Card2_Form.class);

                                startActivity(intent);
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
                else {
                    Toast.makeText(Detail_Submitted.this,"Please fill all the answers",Toast.LENGTH_SHORT).show();
                }
            }
        } );

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Users");
        dbref.keepSynced(true);
        dbref.orderByChild("uid").equalTo(userid).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                     User user = dataSnapshot1.getValue( User.class);
                    name.setText( user.getName() );
                    emailid.setText( user.getEmail() );
                    contact.setText( user.getContactn() );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
