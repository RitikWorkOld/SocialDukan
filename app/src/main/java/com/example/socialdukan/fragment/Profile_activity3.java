package com.example.socialdukan.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity3 extends AppCompatActivity {
    TextView schoolname,coursename,percentage;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity3 );
        schoolname=findViewById( R.id.dip_name );

        coursename=findViewById( R.id.course_name );
        percentage=findViewById( R.id.percentage_dip );
    }
    @Override
    protected void onStart() {
        super.onStart();
        reff= FirebaseDatabase.getInstance().getReference().child( "Profile" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        reff.keepSynced(true);
        reff.orderByChild("uid").equalTo("dip"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                for (DataSnapshot dataSnapshot : dataSnapshot1.getChildren()){

                    Diploma_md diploma_md = dataSnapshot.getValue(Diploma_md.class);

                    schoolname.setText(diploma_md.getDipclgname());

                     coursename.setText(diploma_md.getDipcorsname());
                    percentage.setText(diploma_md.getDippercentage());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
