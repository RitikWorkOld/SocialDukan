package com.example.socialdukan.Student.fragment.other_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Internship.ApplyIntern;
import com.example.socialdukan.Student.fragment.Internship.InternDetail;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.example.socialdukan.Student.fragment.other_services.model.card_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Card2_detail extends AppCompatActivity {
    DatabaseReference databaseReferencedetail;
    ImageView cmpimage;
    TextView infl_name,brandname,brandname2,pymnt_type,location,genre,insta_handle,link,primary,campaign,descri ;
    String id,userid;
    Button apply_btn,applied_btn;
    private DatabaseReference  databaseReference;
    private String description;

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("InfluencerDetailSubmitted");
        dbref.keepSynced(true);
        dbref.orderByChild("influencerid").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    apply_btn.setVisibility(View.GONE);
                    applied_btn.setVisibility(View.VISIBLE);
                }
                else {
                    apply_btn.setVisibility(View.VISIBLE);
                    applied_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card2_detail );
        descri=findViewById( R.id.d );
        infl_name=findViewById( R.id.infl_name );   //influe name
    brandname=findViewById( R.id.brand_name );  //infl name same

        id = getIntent().getStringExtra("id");
        userid = getIntent().getStringExtra("userid");

        brandname2=findViewById( R.id.brand_name2);


        applied_btn=findViewById( R.id.alr_applied );
    pymnt_type=findViewById( R.id.pymnt_type );
    location=findViewById( R.id.location1 );
    genre=findViewById( R.id.genre );           //category
    insta_handle=findViewById( R.id.insta_handle );
    link=findViewById( R.id.link );
    cmpimage=findViewById( R.id.icd_cmpimg );
    primary=findViewById( R.id.primary );
    apply_btn=findViewById( R.id.apply_btn );
    campaign=findViewById( R.id.campaign );




        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("InfluencerCard");
        databaseReferencedetail.keepSynced(true);
        databaseReferencedetail.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    card_model card_model = dataSnapshot1.getValue( card_model.class);

                    Picasso.get().load(card_model.intimguri).resize(400,400).into(cmpimage);
                    infl_name.setText(card_model.getCollab_name());
                    brandname.setText(card_model.getCollab_name());
                    location.setText(card_model.getLocation());
                    brandname2.setText(card_model.getCollab_name());
                    pymnt_type.setText(card_model.getPaymenttype());
                    genre.setText(card_model.getCategory());
                    insta_handle.setText(card_model.getInstahandle());
                    link.setText(card_model.getLink());
                    campaign.setText( card_model.getCampaign() );
                    primary.setText(card_model.getPrimary());
              descri.setText( card_model.getDescrip() );
              final String panel_userid=card_model.getUserid();

                    apply_btn.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent( Card2_detail.this, Detail_Submitted.class);
                            intent.putExtra("id",id);
                            intent.putExtra("userid",userid);
                            intent.putExtra("panel_userid",panel_userid);
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
