package com.example.socialdukan.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.R;
import com.example.socialdukan.Studentdetail;
import com.example.socialdukan.addexp1_model;
import com.example.socialdukan.addexp1_viewholder;
import com.example.socialdukan.addexp_model;
import com.example.socialdukan.addexp_viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_activity3 extends AppCompatActivity {
    DatabaseReference databaseReferenceexprv;
    FirebaseRecyclerOptions<addexp_model> optionsexp;
    FirebaseRecyclerAdapter<addexp_model, addexp_viewholder> adapterexp;
    RecyclerView rv_exp;


    @Override
    protected void onStart() {
        super.onStart();
        adapterexp.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterexp.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_activity3 );

        rv_exp = findViewById( R.id.rv_exp );
        rv_exp.setHasFixedSize( true );
        rv_exp.setLayoutManager( new LinearLayoutManager( this ) );

        databaseReferenceexprv = FirebaseDatabase.getInstance().getReference().child( "Profile" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "cmpexp" );
        databaseReferenceexprv.keepSynced( true );

        optionsexp = new FirebaseRecyclerOptions.Builder<addexp_model>().setQuery( databaseReferenceexprv, addexp_model.class ).build();

        adapterexp = new FirebaseRecyclerAdapter<addexp_model, addexp_viewholder>( optionsexp ) {

            @Override
            protected void onBindViewHolder(@NonNull final addexp_viewholder holder, int position, @NonNull final addexp_model model) {
                holder.companynamelayout.setText( model.getCompanyname() );
                holder.companystartlayout.setText( model.getCompanystart() );
                holder.companyendlayout.setText( model.getCompanyend() );
                holder.companyrolelayout.setText( model.getCompanyrole() );
                holder.companybenefitslayout.setText( model.getCompanybenefits() );


                holder.companynamelayout.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.expand == false) {
                            holder.companystartlayout.setVisibility( View.VISIBLE );
                            holder.companyendlayout.setVisibility( View.VISIBLE );
                            holder.companyrolelayout.setVisibility( View.VISIBLE );
                            holder.companybenefitslayout.setVisibility( View.VISIBLE );

                            holder.expand = true;

                        } else {
                            holder.companystartlayout.setVisibility( View.GONE );
                            holder.companyendlayout.setVisibility( View.GONE );
                            holder.companyrolelayout.setVisibility( View.GONE );
                            holder.companybenefitslayout.setVisibility( View.GONE );
                            holder.editexp.setVisibility( View.GONE );
                            holder.cancelbtn.setVisibility( View.GONE );
                            holder.expand = false;
                        }
                    }
                } );
            }

            @NonNull
            @Override
            public addexp_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new addexp_viewholder( LayoutInflater.from( Profile_activity3.this ).inflate( R.layout.exp_card_layout, parent, false ) );
            }



        };
        rv_exp.setAdapter(adapterexp);
        adapterexp.startListening();
    }
}
