package com.example.socialdukan.Student.fragment.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.profile.models.College_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity4 extends AppCompatActivity {
    TextView collegename,college_dep,college_starty,college_endy,coursename,percentage;
    DatabaseReference reff;
    ImageView cross_btn;
    Button okay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity4 );
        collegename=findViewById( R.id.clg_name );
        college_dep=findViewById( R.id.collegedept );
        college_starty=findViewById( R.id.clg_start );
        college_endy=findViewById( R.id.clg_end );
        coursename=findViewById( R.id.course_name );
        percentage=findViewById( R.id.percentage_clg );
        cross_btn=findViewById( R.id.cross_btn_rf );
        okay_btn=findViewById( R.id.okay );
        coursename=findViewById( R.id.course_name );

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
        reff.orderByChild("uid").equalTo("clg"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                for (DataSnapshot dataSnapshot : dataSnapshot1.getChildren()){

                    College_md college_md = dataSnapshot.getValue(College_md.class);

                    collegename.setText(college_md.getCollegename());
                    coursename.setText(college_md.getCollegecourse());
                    college_dep.setText(college_md.getCollegedept());
                    college_starty.setText(college_md.getCollegestart());
                    college_endy.setText(college_md.getCollegeend());
                    percentage.setText(college_md.getCollegeper());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
