package com.social.socialdukan.Student.fragment.Event;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdukan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Miscellaneous.User;

public class JoinTeamIndi extends AppCompatActivity {

    String maxmem,minmem;
    EditText teamname;
    EditText member1,member2,member3,member4,member5,member6,member7,member8,member9,member10;
    String mem1,mem2,mem3,mem4,mem5,mem6,mem7,mem8,mem9,mem10;
    Button next_btn;
    String teamid;
    String useremail,usernumber,username,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join_team );

        teamid = FirebaseDatabase.getInstance().getReference().child( "EventRegistration" ).push().getKey();

        member1 = findViewById( R.id.member1 );
        member2 = findViewById( R.id.member2 );
        member3 = findViewById( R.id.member3 );
        member4 = findViewById( R.id.member4 );
        member5 = findViewById( R.id.member5 );
        member6 = findViewById( R.id.member6 );
        member7 = findViewById( R.id.member7 );
        member8 = findViewById( R.id.member8 );
        member9 = findViewById( R.id.member9 );
        member10 = findViewById( R.id.member10 );

        next_btn = findViewById( R.id.next_btn );
        teamname = findViewById( R.id.team_name );

        member3.setVisibility( View.GONE );
        member4.setVisibility( View.GONE );
        member5.setVisibility( View.GONE );
        member6.setVisibility( View.GONE );
        member7.setVisibility( View.GONE );
        member8.setVisibility( View.GONE );
        member9.setVisibility( View.GONE );
        member10.setVisibility( View.GONE );
teamname.setVisibility( View.GONE );
member1.setHint( "Name" );
member2.setHint( "Email" );

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" );
        databaseReference.keepSynced( true );
        databaseReference.orderByChild( "uid" ).equalTo( FirebaseAuth.getInstance().getCurrentUser().getUid() ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue( User.class );

                    useremail = user.getEmail();
                    username = user.getName();
                    usernumber = user.getContactn();
                    uid = user.getUid();
                    member1.setText( username );
                    member2.setText( useremail );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
next_btn.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
} );
    }
}