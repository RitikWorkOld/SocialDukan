package com.example.socialdukan.Student.fragment.Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Event.FAQ.Faq_manager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Events_detail extends AppCompatActivity {
    TextView Title_dt;
    TextView Descp_dt;
    TextView Desc1_dt;
    TextView Desc2_dt;
    Button Register_dt,registered;
    ImageView Mimg_dt;
    TextView readless;
    DatabaseReference databaseReferencedetail;
    String key;


    TextView faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_events_detail );
        readless = findViewById(R.id.read_less_events);

        Title_dt = findViewById(R.id.event_title);
        Descp_dt = findViewById(R.id.event_descp);
        Desc1_dt = findViewById(R.id.event_descp1);
        Desc2_dt = findViewById(R.id.event_descp2);
        Mimg_dt = findViewById(R.id.event_main_img1);
        Register_dt = findViewById(R.id.register_events);

        faq = findViewById( R.id.faq_btn );
        registered =findViewById(R.id.registered);
        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReferencedetail.keepSynced(true);
        key = getIntent().getStringExtra("key");
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

                        }
                    } );
                    Register_dt.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Events_detail.this, Register_event.class);
                            intent.putExtra("key",key);
                            startActivity(intent);

                        }
                    } );


                    faq.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Events_detail.this, Faq_manager.class);
                            intent.putExtra("key",key);
                            startActivity(intent);
                        }
                    } );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
