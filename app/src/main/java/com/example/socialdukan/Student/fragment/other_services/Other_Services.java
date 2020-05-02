package com.example.socialdukan.Student.fragment.other_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Notifications;


public class Other_Services extends Fragment {
CardView card1,card2,card3,card4,card5;
    ImageView notification_btn;


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
        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
