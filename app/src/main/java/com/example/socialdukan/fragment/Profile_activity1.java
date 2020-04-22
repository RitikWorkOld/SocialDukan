package com.example.socialdukan.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.socialdukan.Pers_detail;
import com.example.socialdukan.R;
import com.example.socialdukan.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Profile_activity1 extends AppCompatActivity {
TextView dob,address,occ,wa_no;

    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity1 );
        dob = findViewById( R.id.dob_text );
        address = findViewById( R.id.add_text );
        occ = findViewById( R.id.curr_occ_text );
        wa_no = findViewById( R.id.wa_no_text );

            /*public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot : dataSnapshot.getChildren()) {
                    Pers_detail pers_detail = dataSnapshot.getValue( Pers_detail.class );
                    dob1 = pers_detail.getDob();
                    address1 = pers_detail.getAdress();
                    occ1 = pers_detail.getOccupation();
                    wa_no1 = pers_detail.getWanumber();

                    dob.setText( pers_detail.getDob() );
                    address.setText( pers_detail.getAdress() );
                    occ.setText( pers_detail.getOccupation() );
                    wa_no.setText( pers_detail.getWanumber() );

                }
            }*/




    }

    @Override
    protected void onStart() {
        super.onStart();
        reff=FirebaseDatabase.getInstance().getReference().child( "Profile" ).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("personaldet");
        reff.keepSynced(true);
        reff.orderByChild("uid").equalTo( FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dob1 = dataSnapshot.child(  "dob").getValue().toString();
                String address1=dataSnapshot.child( "adress" ).getValue().toString();
                String occ1=dataSnapshot.child( "occupation" ).getValue().toString();
                String wa_no1=dataSnapshot.child( "wanumber" ).getValue().toString();



                dob.setText( dob1);
                address.setText( address1 );
                occ.setText( occ1 );
                wa_no.setText( wa_no1 );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
