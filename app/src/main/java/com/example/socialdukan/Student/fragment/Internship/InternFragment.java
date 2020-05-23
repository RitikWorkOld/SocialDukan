package com.example.socialdukan.Student.fragment.Internship;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.example.socialdukan.Student.Notifications.Notifications;
import com.example.socialdukan.Student.Notifications.Notifications_Dots;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.example.socialdukan.Student.fragment.Internship.model.internall_vh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InternFragment extends Fragment {

    BucketRecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<internall_md> optionsinternall;
    FirebaseRecyclerAdapter<internall_md, internall_vh> adapterinternall;
    ImageView notification_btn;
    private View no_app;

    ImageView notification_badge;

    public InternFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_intern,container,false);
        // Inflate the layout for this fragment
        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        notification_badge = (ImageView)view.findViewById(R.id.notificationbadge);
        no_app=view.findViewById( R.id.no_app );

        notification_badge.setVisibility(View.GONE);
        DatabaseReference databaseReferencenot = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
        rv_internall = view.findViewById(R.id.rv_internall);
        rv_internall.showIfEmpty( no_app );
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(container.getContext()));


        drinternall = FirebaseDatabase.getInstance().getReference().child("Internships");
        drinternall.keepSynced(true);
        Query query = drinternall.orderByChild("admin_status" ).equalTo( "Accepted" );
        optionsinternall = new FirebaseRecyclerOptions.Builder<internall_md>().setQuery(query,internall_md.class).build();

        adapterinternall = new FirebaseRecyclerAdapter<internall_md, internall_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull internall_vh holder, int position, @NonNull final internall_md model) {
                holder.cmpname.setText(model.getIntname());
                holder.cmpsubname.setText(model.getCmpname());
                Picasso.get().load(model.getIntimguri()).resize(400,400).into(holder.cmpimg);
                holder.location.setText(model.getLocation());
                holder.amount.setText(model.getAmount());
                holder.duration.setText(model.getDuration());

                notification_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(getActivity(), Notifications.class);
                        startActivity(intent);

                    }
                });

                holder.layout_card.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), InternDetail.class);
                        intent.putExtra( "key",model.getId() );

                        startActivity( intent );
                    }
                } );

            }

            @NonNull
            @Override
            public internall_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new internall_vh(LayoutInflater.from(view.getContext()).inflate(R.layout.internship_card_layout, parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();

        return view;


    }

}