package com.example.socialdukan.Student.fragment.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.profile.models.Diploma_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity3 extends AppCompatActivity {
    TextView schoolname,coursename,percentage;
    DatabaseReference reff;
    ImageView cross_btn;
    Button okay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity3 );
        schoolname=findViewById( R.id.dip_name );
        cross_btn=findViewById( R.id.cross_btn_rf );
        okay_btn=findViewById( R.id.okay );
        coursename=findViewById( R.id.course_name );
        percentage=findViewById( R.id.percentage_dip );
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
