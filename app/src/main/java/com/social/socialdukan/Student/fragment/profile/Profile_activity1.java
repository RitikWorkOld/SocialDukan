package com.social.socialdukan.Student.fragment.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.fragment.profile.models.School_ten_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.fragment.profile.models.School_ten_md;

public class Profile_activity1 extends AppCompatActivity {
    TextView schoolname,startyear,endyear,board,percentage;
    DatabaseReference reff;
    ImageView cross_btn;
    Button okay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity1 );
        schoolname=findViewById( R.id.schoolname10 );
        startyear=findViewById( R.id.schlstarty10 );
        endyear=findViewById( R.id.schlendy10 );
        board=findViewById( R.id.board );
        percentage=findViewById( R.id.percentage10 );
        cross_btn=findViewById( R.id.cross_btn_rf );
        okay_btn=findViewById( R.id.okay );

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
