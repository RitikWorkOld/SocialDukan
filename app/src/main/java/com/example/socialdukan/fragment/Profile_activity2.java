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

public class Profile_activity2 extends AppCompatActivity {
TextView schoolname,startyear,endyear,board,percentage;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity2 );
        schoolname=findViewById( R.id.schoolname10 );
        startyear=findViewById( R.id.schlstarty10 );
        endyear=findViewById( R.id.schlendy10 );
        board=findViewById( R.id.board );
        percentage=findViewById( R.id.percentage10 );
    }
    @Override
    protected void onStart() {
        super.onStart();
        reff= FirebaseDatabase.getInstance().getReference().child( "Profile" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        reff.keepSynced(true);
        reff.orderByChild("uid").equalTo("sch10"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                for (DataSnapshot dataSnapshot : dataSnapshot1.getChildren()){

                    School_ten_md school_ten_md = dataSnapshot.getValue(School_ten_md.class);

                    schoolname.setText(school_ten_md.getSchoolname());
                    startyear.setText(school_ten_md.getSchoolstarty());
                    endyear.setText(school_ten_md.getSchoolendy());
                    board.setText(school_ten_md.getSchoolboard());
                    percentage.setText(school_ten_md.getSchoolper());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
