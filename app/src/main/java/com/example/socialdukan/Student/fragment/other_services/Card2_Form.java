package com.example.socialdukan.Student.fragment.other_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.R;


import com.example.socialdukan.Student.fragment.other_services.model.card_model;
import com.example.socialdukan.Student.fragment.other_services.model.card_vh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Card2_Form extends AppCompatActivity {
    RecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<card_model> optionsinternall;
    FirebaseRecyclerAdapter<card_model, card_vh> adapterinternall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card2__form );

        rv_internall = findViewById(R.id.rv_internall);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(this));
        drinternall = FirebaseDatabase.getInstance().getReference().child("InfluencerCard");
        drinternall.keepSynced(true);
        optionsinternall = new FirebaseRecyclerOptions.Builder<card_model>().setQuery(drinternall,card_model.class).build();
        adapterinternall = new FirebaseRecyclerAdapter<card_model, card_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull card_vh holder, int position, @NonNull final card_model model) {
                holder.infl_name.setText(model.getCollab_name());
                holder.category.setText(model.getCategory());
                Picasso.get().load(model.getIntimguri()).into(holder.cmpimg);
                holder.location.setText(model.getLocation());
                holder.date.setText(model.getDate());

                holder.detail_btn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Card2_detail.class);
                        intent.putExtra("key",model.getKey());
                        startActivity( intent );
                    }
                } );


            }

            @NonNull
            @Override
            public card_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new card_vh(LayoutInflater.from( Card2_Form.this).inflate(R.layout.influencer_card_layout,parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();

    }
}
