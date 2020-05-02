package com.example.socialdukan.Student.fragment.Event;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.socialdukan.Student.Notifications.Notifications;
import com.example.socialdukan.Student.fragment.Internship.InternDetail;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment {

    RecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<events_md> optionsinternall;
    FirebaseRecyclerAdapter<events_md, events_vh> adapterinternall;
    ImageView notification_btn;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event,container,false);
        // Inflate the layout for this fragment
        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        rv_internall = view.findViewById(R.id.recycler);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(container.getContext()));

        drinternall = FirebaseDatabase.getInstance().getReference().child("Events");
        drinternall.keepSynced(true);

        optionsinternall = new FirebaseRecyclerOptions.Builder<events_md>().setQuery(drinternall,events_md.class).build();

        adapterinternall = new FirebaseRecyclerAdapter<events_md, events_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull events_vh holder, int position, @NonNull final events_md model) {
                holder.Title.setText(model.getEventname());
                holder.Descp.setText(model.getEvent_desc());
                Picasso.get().load(model.getIntimguri()).into(holder.Mimguri);
                notification_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(getActivity(), Notifications.class);
                        startActivity(intent);

                    }
                });
                holder.read_more_ebtn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Events_detail.class);
                        intent.putExtra("key",model.getKey());
                        startActivity( intent );
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
