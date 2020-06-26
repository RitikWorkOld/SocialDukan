package com.social.socialdukan.Student.fragment.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Internship.model.EventRegistered;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Event_Pass extends AppCompatActivity {
TextView event_na,name,email,cont_number,clg_name;
    DatabaseReference databaseReferencedetail,databaseReferencedetail1;
    CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_event__pass );
        event_na=findViewById( R.id.event_name );
        img=findViewById( R.id.img );
        name=findViewById( R.id.name );
        email=findViewById( R.id.email );
        cont_number=findViewById( R.id.number );
        clg_name=findViewById( R.id.clg_name );

        final String event_name = getIntent().getExtras().getString("Name");
        final String uid = getIntent().getExtras().getString("uid");
        event_na.setText( event_name );

        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("EventRegistration");
        databaseReferencedetail.keepSynced(true);

        databaseReferencedetail.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {   databaseReferencedetail1 = FirebaseDatabase.getInstance().getReference().child("Users");
                    databaseReferencedetail1.keepSynced(true);

                    databaseReferencedetail1.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                final User use = dataSnapshot1.getValue(User.class);
                                //   Picasso.get().load(Mimguri).resize(400,400).into(img);

                                if (use.profileimg!=null){
                                   // Picasso.get().load(bnd_helper.getProfileimg()).resize( 400,400 ).into(user_img);
                                    Picasso.get().load(use.getProfileimg()).resize(400,400).into(img);
                                }
                                else {
                                    img.setImageResource(R.drawable.user);
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    final EventRegistered event = dataSnapshot1.getValue(EventRegistered.class);
                 //   Picasso.get().load(Mimguri).resize(400,400).into(img);

name.setText( event.getUsername() );
email.setText( event.getUseremail() );
cont_number.setText( event.getUsernumber() );
clg_name.setText( event.getCollegename() );




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}