package com.social.socialdukan.Student.fragment.other_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.Notifications.Notifications;
import com.social.socialdukan.Student.Notifications.Notifications_Dots;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Notifications.Notifications;
import com.social.socialdukan.Student.Notifications.Notifications_Dots;


public class Other_Services extends Fragment {
CardView card1,card2,card3,card4,card5;
    ImageView notification_btn,notification_badge;


    public Other_Services() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_other__services,container,false);

        card1=view.findViewById( R.id.card_main );
        card2=view.findViewById( R.id.card2 );
        card3=view.findViewById( R.id.card3 );
        card4=view.findViewById( R.id.card4 );
        card5=view.findViewById( R.id.card5 );
        notification_badge = (ImageView)view.findViewById(R.id.notificationbadge);

        notification_badge.setVisibility(View.GONE);
        DatabaseReference databaseReferencenot = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                .child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReferencenot.keepSynced(true);
        databaseReferencenot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notifications_Dots notifications_dots = dataSnapshot.getValue(Notifications_Dots.class);
                if (notifications_dots != null)
                {
                    if (notifications_dots.getDotstatus().equals("yes")){
                        notification_badge.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReferencenotup = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReferencenotup.child("dotstatus").setValue("no");
                databaseReferencenotup.keepSynced(true);
                Intent intent = new Intent(getActivity(), Notifications.class);
                startActivity(intent);

            }
        });
        card1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Card1_Form.class);

                startActivity( intent );
            }
        } );
        card2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Card2_Form.class);

                startActivity( intent );
            }
        } );

        card3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Card3_Form.class);
                startActivity( intent );
            }
        } );

        card4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Card4_Form.class);
                startActivity( intent );
            }
        } );

        card5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Card5_Form.class);
                startActivity( intent );
            }
        } );




        return view;
    }
}
