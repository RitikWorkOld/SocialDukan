package com.example.socialdukan.Student.fragment.Event.FAQ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.socialdukan.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Faq_manager extends AppCompatActivity {
    RecyclerView rv_internall;
    private ImageView close_btn;
    private ArrayList<Faq_model> arrayList;
    DatabaseReference drinternall;
    String key;
    FirebaseRecyclerOptions<Faq_model> optionsinternall;
    FirebaseRecyclerAdapter<Faq_model, Faq_viewholder> adapterinternall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_faq_manager );
        close_btn = findViewById(R.id.close_btn);
        rv_internall = findViewById(R.id.rv_faq_events);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(this));
        key = getIntent().getStringExtra("key");
        drinternall = FirebaseDatabase.getInstance().getReference().child("Events").child(key).child("faq").child(key);
        drinternall.keepSynced(true);
        arrayList = new ArrayList<Faq_model>();
        optionsinternall = new FirebaseRecyclerOptions.Builder<Faq_model>().setQuery(drinternall,Faq_model.class).build();
        adapterinternall = new FirebaseRecyclerAdapter<Faq_model, Faq_viewholder>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull Faq_viewholder holder, int i, @NonNull final Faq_model model) {
                holder.question.setText( model.getQuestion() );
                holder.answer.setText( model.getAnswer() );



            }

            @NonNull
            @Override
            public Faq_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Faq_viewholder(LayoutInflater.from( Faq_manager.this).inflate(R.layout.faq_event_layout,parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
