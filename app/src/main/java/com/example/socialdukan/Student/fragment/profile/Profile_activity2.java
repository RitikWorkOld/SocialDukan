package com.example.socialdukan.Student.fragment.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.profile.models.School_twe_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity2 extends AppCompatActivity {
TextView schoolname,startyear,endyear,board,percentage,stream;
    DatabaseReference reff;
    ImageView cross_btn;
    Button okay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity2 );
        schoolname=findViewById( R.id.schoolname12 );
        startyear=findViewById( R.id.schlstarty12 );
        endyear=findViewById( R.id.schlendy12 );
        board=findViewById( R.id.board1 );
        cross_btn=findViewById( R.id.cross_btn_rf );
        okay_btn=findViewById( R.id.okay );
        percentage=findViewById( R.id.percentage12 );
        stream=findViewById( R.id.stream );
        cross_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        okay_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }
    @Override
    protected void onStart() {
        super.onStart();
        reff= FirebaseDatabase.getInstance().getReference().child( "Profile" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        reff.keepSynced(true);
        reff.orderByChild("uid").equalTo("sch12"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                for (DataSnapshot dataSnapshot : dataSnapshot1.getChildren()){

                    School_twe_md school_twe_md = dataSnapshot.getValue(School_twe_md.class);

                    schoolname.setText(school_twe_md.getSchoolname());
                    startyear.setText(school_twe_md.getSchoolstarty());
                    endyear.setText(school_twe_md.getSchoolendy());
                    board.setText(school_twe_md.getSchoolboard());
                    percentage.setText(school_twe_md.getSchoolper());
                    stream.setText( school_twe_md.getSchoolstream() );



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
}
