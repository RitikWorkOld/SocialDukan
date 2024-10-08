package com.social.socialdukan.Student.fragment.Event;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.R;

/**
 * A simple {@link Fragment} subclass.
 */


import android.content.Intent;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.social.socialdukan.Student.Contact_Us;
import com.social.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.social.socialdukan.Student.Notifications.Notifications;
import com.social.socialdukan.Student.Notifications.Notifications_Dots;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.social.socialdukan.Student.Notifications.Notifications;
import com.social.socialdukan.Student.Notifications.Notifications_Dots;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment {
String key;
    BucketRecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<events_md> optionsinternall;
    FirebaseRecyclerAdapter<events_md, events_vh> adapterinternall;
    ImageView notification_btn,notification_badge;
    private LinearLayoutManager mLayoutManager;
    FloatingActionButton help_fab;
private View no_app;
    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event,container,false);
        // Inflate the layout for this fragment

        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        notification_badge = (ImageView)view.findViewById(R.id.notificationbadge);
        no_app=view.findViewById( R.id.no_app );
        help_fab=view.findViewById( R.id.help_fab );
        help_fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Us.class);


                startActivity( intent );
            }
        } );
        notification_badge.setVisibility(View.GONE);

        DatabaseReference databaseReferencenot = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReferencenot.keepSynced(true);
        databaseReferencenot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notifications_Dots notifications_dots = dataSnapshot.getValue(Notifications_Dots.class);
                if (notifications_dots != null) {
                    if (notifications_dots.getDotstatus().equals("no")) {
                        notification_badge.setVisibility(View.VISIBLE);  // Show badge
                    } else {
                        notification_badge.setVisibility(View.GONE);     // Hide badge when dotstatus is "no"
                    }
                } else {
                    notification_badge.setVisibility(View.GONE);         // Hide badge by default if no dotstatus is found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });

        rv_internall = view.findViewById(R.id.recycler);

        rv_internall.showIfEmpty( no_app );

        mLayoutManager=new LinearLayoutManager(container.getContext());
        mLayoutManager.setReverseLayout( true );
        mLayoutManager.setStackFromEnd(true);

        rv_internall.setLayoutManager(mLayoutManager  );
        rv_internall.setAdapter( adapterinternall );
        drinternall = FirebaseDatabase.getInstance().getReference().child("Events");
        drinternall.keepSynced(true);
        Query query = drinternall.orderByChild("status" ).equalTo("Accepted");
        optionsinternall = new FirebaseRecyclerOptions.Builder<events_md>().setQuery(query,events_md.class).build();

        adapterinternall = new FirebaseRecyclerAdapter<events_md, events_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull events_vh holder, int position, @NonNull final events_md model) {
                holder.Title.setText(model.getEventname());
                holder.Descp.setText(model.getEventdesc());
                if (model.getImageUrl() != null) {
                    Picasso.get().load(model.getImageUrl()).resize(400, 400).into(holder.Mimguri);
                }
                holder.event_date.setText( model.getEventdate() );
                Log.d("CHECK_IT",model.getEventname()+model.getEventdesc()+model.getEventdate());
String event_name = model.getEventname();
                String Title = model.getEventname();
                String Descp = model.getEventdesc();
                String Desc1 = model.getEventdesc();
                String Desc2 = model.getEventdesc();
                String Mimguri = model.getImageUrl();
                String number_of_member=model.getMax_number();
                String event_date=model.getEventdate();
                String event_fb_link=model.getEvent_fb_link();
                        String event_insta_handle=model.getEvent_insta_handle();
                        String website_link=model.getWebsite_link();
                        String max_number=model.getMax_number();
                        String min_number=model.getMin_number();
                        String type_event=model.getType_event();
                key=model.getEventId();
                String location=model.getLocation();
String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Ensure all required data is put into the Bundle
                final Bundle bundle = new Bundle();
                bundle.putString("Name", event_name);
                bundle.putString("Title", Title);
                bundle.putString("Descp", Descp);
                bundle.putString("Desc1", Desc1);
                bundle.putString("Desc2", Desc2);
                bundle.putString("Mimguri", Mimguri);
                bundle.putString("number_of_member", number_of_member);
                bundle.putString("event_date", event_date);
                bundle.putString("uid", uid);
                bundle.putString("max_number", max_number);
                bundle.putString("min_number", min_number);
                bundle.putString("type_event", type_event);
                bundle.putString("location", location);
                bundle.putString("key", key);
                bundle.putString("amt", model.getAmount());
                bundle.putString("fb", event_fb_link);
                bundle.putString("insta", event_insta_handle);
                bundle.putString("website", website_link);


                notification_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseReference databaseReferencenotup = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReferencenotup.child("dotstatus").setValue("yes");
                        databaseReferencenotup.keepSynced(true);
                        Intent intent = new Intent(getActivity(), Notifications.class);
                        startActivity(intent);

                    }
                });
                holder.read_more_ebtn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Event_detail events_details = new Event_detail();
                        events_details.setArguments(bundle);

                        fragmentTransaction.replace(R.id.frame,events_details);
                        fragmentTransaction.addToBackStack("tech");
                        fragmentTransaction.commit();
                    }
                } );


            }

            @NonNull
            @Override
            public events_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new events_vh(LayoutInflater.from(view.getContext()).inflate(R.layout.activity_events_vh, parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();

        return view;
    }


}
