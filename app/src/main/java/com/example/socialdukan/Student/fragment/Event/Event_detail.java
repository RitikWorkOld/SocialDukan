package com.example.socialdukan.Student.fragment.Event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Event.EventFragment;
import com.example.socialdukan.Student.fragment.Event.FAQ.FAQ_Managr;
import com.example.socialdukan.Student.fragment.Event.events_md;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Event_detail extends Fragment {
    TextView Title_dt,insta_handle,link,event_location,date,event_type,fb_handle,fb_title,link_title,insta_title,participants,range,amount,indi_team;
    TextView Descp_dt;
    TextView Desc1_dt;
    TextView Desc2_dt;
    Button Register_dt,registered;
    ImageView Mimg_dt;
    TextView readless;
    DatabaseReference databaseReferencedetail;

    TextView faq;

    public Event_detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     final   View view= inflater.inflate( R.layout.fragment_event_detail, container, false );
        final Bundle bundle = getArguments();

        final String Title = bundle.getString("Title");
        String Descp = bundle.getString("Descp");
        String Desc1 = bundle.getString("Desc1");
        String Desc2 = bundle.getString("Desc2");
        String Mimguri = bundle.getString("Mimguri");
        final String key = bundle.getString("key");
        final String number_of_member = bundle.getString("number_of_member");
        final String event_date = bundle.getString("event_date");

        final String max_number = bundle.getString("max_number");
        final String min_number = bundle.getString("min_number");
        final String type_event = bundle.getString("type_event");
        final String location = bundle.getString("location");

        readless = view.findViewById(R.id.read_less_events);
        final EventFragment eventFragment=new EventFragment();
        Title_dt = view.findViewById(R.id.event_title);
        participants=view.findViewById( R.id.participants );
        fb_handle=view.findViewById( R.id.fb_handle );
        range=view.findViewById( R.id.range );
        fb_title=view.findViewById( R.id.fb_title );
        insta_title=view.findViewById( R.id.insta_title );
        amount=view.findViewById( R.id.amount );
        indi_team=view.findViewById( R.id.per_team_indiv );
        link_title=view.findViewById( R.id.link_title );

        event_location=view.findViewById( R.id.location_desc );
        date=view.findViewById( R.id.event_date );
        event_type=view.findViewById( R.id.event_type );

        Descp_dt = view.findViewById(R.id.event_descp);
        Desc1_dt = view.findViewById(R.id.event_descp1);

        insta_handle=view.findViewById( R.id.insta_handle );
        link=view.findViewById( R.id.link );

        Desc2_dt = view.findViewById(R.id.event_descp2);
        Mimg_dt = view.findViewById(R.id.event_main_img1);
        Register_dt = view.findViewById(R.id.register_events);

        faq = view.findViewById( R.id.faq_btn );
        registered =view.findViewById(R.id.registered);
        Title_dt.setText(Title);
        Descp_dt.setText(Descp);
        Desc1_dt.setText(Desc1);
        Desc2_dt.setText(Desc2);
        event_location.setText( location );
        date.setText( event_date );
        event_type.setText( type_event );

        Picasso.get().load(Mimguri).resize(400,400).into(Mimg_dt);

        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReferencedetail.keepSynced(true);

        databaseReferencedetail.orderByChild("eventid").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    events_md valueintern = dataSnapshot1.getValue(events_md.class);
                    if(!valueintern.getAmount().equals( "0" )) {
                    amount.setText( "Rs "+valueintern.getAmount() );
                    indi_team.setText( "( "+valueintern.getIndi_or_team()+" )" );

                }
                    if(valueintern.getAmount().equals( "0" )) {
                        amount.setText( "Free");
                        indi_team.setVisibility( View.GONE );

                    }
                    participants.setText( valueintern.getNumber_of_member() );
                    if(valueintern.getNumber_of_member().equals( "Team" )){
                        range.setVisibility( View.VISIBLE );
                        range.setText( valueintern.getRange() );
                    }

                    if(!valueintern.getNumber_of_member().equals( "Team" )){
                        range.setVisibility( View.GONE );
                    }
if(valueintern.getEvent_insta_handle().equals( "" )){
    insta_handle.setVisibility( View.GONE );
    insta_title.setVisibility( View.GONE );

}
                    if(valueintern.getEvent_website_link().equals( "" )){
                        link.setVisibility( View.GONE );
                        link_title.setVisibility( View.GONE );

                    }
                    if(valueintern.getEvent_fb_link().equals( "" )){
                        fb_handle.setVisibility( View.GONE );
                        fb_title.setVisibility( View.GONE );

                    }

                    //not
                    if(!valueintern.getEvent_insta_handle().equals( "" )){
                        insta_handle.setVisibility( View.VISIBLE );
                        insta_title.setVisibility( View.VISIBLE );
                        insta_handle.setText( valueintern.getEvent_insta_handle() );


                    }
                    if(!valueintern.getEvent_website_link().equals( "" )){
                        link.setVisibility( View.VISIBLE );
                        link_title.setVisibility( View.VISIBLE );
                        link.setText( valueintern.getEvent_website_link() );

                    }
                    if(!valueintern.getEvent_fb_link().equals( "" )){
                        fb_handle.setVisibility( View.VISIBLE );
                        fb_title.setVisibility( View.VISIBLE );
                        fb_handle.setText( valueintern.getEvent_fb_link() );

                    }
                    Picasso.get().load(valueintern.intimguri).resize(400,400).into(Mimg_dt);
                    Title_dt.setText(valueintern.eventname);
                    Descp_dt.setText(valueintern.event_desc);
                    Desc1_dt.setText(valueintern.desc1);
                    Desc2_dt.setText(valueintern.desc2);
                    readless.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.popBackStack();

                        }
                    } );
                    Register_dt.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    } );


                    faq.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Bundle bundle = new Bundle();
                            bundle.putString( "key",key);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            FAQ_Managr faq_events_fargment = new FAQ_Managr();
                            faq_events_fargment.setArguments( bundle );

                            fragmentTransaction.replace(R.id.frame,faq_events_fargment);
                            fragmentTransaction.addToBackStack("faq");
                            fragmentTransaction.commit();

                        }
                    } );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}
