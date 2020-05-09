package com.example.socialdukan.Employe.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.Miscellaneous.User;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.example.socialdukan.Student.fragment.Internship.model.internall_vh;
import com.example.socialdukan.Student.fragment.MyDashboard.applied_intern_md;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class Applications_Fragment extends Fragment {
    RecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<User> optionsinternall;
    FirebaseRecyclerAdapter<User, Application_vh> adapterinternall;
    String key,userid,status;
    private FirebaseAuth mFirebaseAuth;
    public Applications_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=  inflater.inflate( R.layout.fragment_applications_, container, false );
        key = getArguments().getString("key");

        rv_internall = view.findViewById(R.id.recycle);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));

        mFirebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child( key );
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("key").equalTo( Objects.requireNonNull( mFirebaseAuth.getCurrentUser() ).getUid()).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    applied_intern_md user = dataSnapshot1.getValue(applied_intern_md.class);
                   userid=user.getUserid();
                   status=user.getStatus();

                    drinternall = FirebaseDatabase.getInstance().getReference().child("Users").child( userid );
                    drinternall.keepSynced(true);

                    optionsinternall = new FirebaseRecyclerOptions.Builder<User>().setQuery(drinternall,User.class).build();

                    adapterinternall = new FirebaseRecyclerAdapter<User, Application_vh>(optionsinternall) {
                        @Override
                        protected void onBindViewHolder(@NonNull Application_vh holder, int position, @NonNull final User model) {
                            holder.cmpname.setText(model.getName());
                            holder.cmpsubname.setText(model.getContactn());
                            Picasso.get().load(model.getProfileimg()).into(holder.cmpimg);


                            holder.layout_card.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), Application_detail.class);

                                    startActivity( intent );
                                }
                            } );

                        }

                        @NonNull
                        @Override
                        public Application_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            return new Application_vh(LayoutInflater.from(view.getContext()).inflate(R.layout.application_vh, parent,false));
                        }
                    };
                    rv_internall.setAdapter(adapterinternall);
                    adapterinternall.startListening();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
