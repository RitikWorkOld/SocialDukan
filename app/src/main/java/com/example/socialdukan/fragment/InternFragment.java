package com.example.socialdukan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class InternFragment extends Fragment {

    RecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<internall_md> optionsinternall;
    FirebaseRecyclerAdapter<internall_md,internall_vh> adapterinternall;

    public InternFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_intern,container,false);
        // Inflate the layout for this fragment

        rv_internall = view.findViewById(R.id.rv_internall);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(container.getContext()));

        drinternall = FirebaseDatabase.getInstance().getReference().child("Internships");
        drinternall.keepSynced(true);

        optionsinternall = new FirebaseRecyclerOptions.Builder<internall_md>().setQuery(drinternall,internall_md.class).build();

        adapterinternall = new FirebaseRecyclerAdapter<internall_md, internall_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull internall_vh holder, int position, @NonNull internall_md model) {
                holder.cmpname.setText(model.getIntname());
                holder.cmpsubname.setText(model.getCmpname());
                Picasso.get().load(model.getIntimguri()).into(holder.cmpimg);
                holder.location.setText(model.getLocation());
                holder.amount.setText(model.getAmount());
                holder.duration.setText(model.getDuration());

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