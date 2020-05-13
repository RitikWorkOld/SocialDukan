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
    TextView Title_dt;
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
        readless = view.findViewById(R.id.read_less_events);
        final EventFragment eventFragment=new EventFragment();

        Title_dt = view.findViewById(R.id.event_title);
        Descp_dt = view.findViewById(R.id.event_descp);
        Desc1_dt = view.findViewById(R.id.event_descp1);
        Desc2_dt = view.findViewById(R.id.event_descp2);
        Mimg_dt = view.findViewById(R.id.event_main_img1);
        Register_dt = view.findViewById(R.id.register_events);

        faq = view.findViewById( R.id.faq_btn );
        registered =view.findViewById(R.id.registered);
        Title_dt.setText(Title);
        Descp_dt.setText(Descp);
        Desc1_dt.setText(Desc1);
        Desc2_dt.setText(Desc2);
        Picasso.get().load(Mimguri).into(Mimg_dt);

        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReferencedetail.keepSynced(true);

        databaseReferencedetail.orderByChild("key").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    events_md valueintern = dataSnapshot1.getValue(events_md.class);

                    Picasso.get().load(valueintern.intimguri).into(Mimg_dt);
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
