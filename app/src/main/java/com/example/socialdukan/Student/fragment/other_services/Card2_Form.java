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


import com.example.socialdukan.Student.Miscellaneous.User;
import com.example.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.example.socialdukan.Student.fragment.other_services.model.card_model;
import com.example.socialdukan.Student.fragment.other_services.model.card_vh;
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

public class Card2_Form extends AppCompatActivity {
    BucketRecyclerView rv_internall;
    DatabaseReference drinternall;
    private LinearLayoutManager mLayoutManager;
    FirebaseRecyclerOptions<card_model> optionsinternall;
    private String userid;
    private View no_app;
    FirebaseRecyclerAdapter<card_model, card_vh> adapterinternall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card2__form );
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
        databaseReference.keepSynced( true );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
             userid=user.getUid();
no_app=findViewById( R.id.no_app );

                rv_internall = findViewById(R.id.rv_internall);

                rv_internall.showIfEmpty( no_app );
                mLayoutManager=new LinearLayoutManager(getApplicationContext());
                mLayoutManager.setReverseLayout( true );
                mLayoutManager.setStackFromEnd(true);

                rv_internall.setLayoutManager(mLayoutManager  );
                rv_internall.setAdapter( adapterinternall );
                drinternall = FirebaseDatabase.getInstance().getReference().child("InfluencerCard");
                Query query = drinternall.orderByChild("id_status" ).equalTo("Accepted");
                drinternall.keepSynced(true);
                optionsinternall = new FirebaseRecyclerOptions.Builder<card_model>().setQuery(query,card_model.class).build();
                adapterinternall = new FirebaseRecyclerAdapter<card_model, card_vh>(optionsinternall) {
                    @Override
                    protected void onBindViewHolder(@NonNull card_vh holder, int position, @NonNull final card_model model) {
                        holder.infl_name.setText(model.getCollab_name());
                        holder.category.setText(model.getCategory());
                        Picasso.get().load(model.getIntimguri()).resize(400,400).into(holder.cmpimg);
                        holder.location.setText(model.getLocation());
                        holder.date.setText(model.getDate());

                        holder.detail_btn.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), Card2_detail.class);
                                intent.putExtra("id",model.getId());
                                intent.putExtra("userid",userid);
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );



    }
}
