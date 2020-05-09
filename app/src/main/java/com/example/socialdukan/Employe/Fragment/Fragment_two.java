package com.example.socialdukan.Employe.Fragment;

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
import android.widget.Toast;

import com.example.socialdukan.Employe.Login_Register_Employe.Dashboard_emp;
import com.example.socialdukan.Employe.Login_Register_Employe.Employe_detail;
import com.example.socialdukan.Employe.Login_Register_Employe.Login_Employe;
import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.Miscellaneous.User;
import com.example.socialdukan.Student.Notifications.Notifications;
import com.example.socialdukan.Student.fragment.Internship.InternDetail;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.example.socialdukan.Student.fragment.Internship.model.internall_vh;
import com.example.socialdukan.Student.fragment.profile.aboutus.about_us;
import com.example.socialdukan.Student.fragment.profile.aboutus.aboutus_md;
import com.example.socialdukan.Student.fragment.profile.aboutus.aboutus_vh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_two extends Fragment {
    RecyclerView rv_internall;
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<internall_md> optionsinternall;
    FirebaseRecyclerAdapter<internall_md, internall_vh> adapterinternall;
    private FirebaseAuth mFirebaseAuth;
    private String cmpid,eid;
    public Fragment_two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate( R.layout.fragment_two, container, false );
        mFirebaseAuth = FirebaseAuth.getInstance();

        rv_internall = view.findViewById(R.id.recyclerview);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(container.getContext()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Employe");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("eid").equalTo( Objects.requireNonNull( mFirebaseAuth.getCurrentUser() ).getUid()).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Employe user = dataSnapshot1.getValue(Employe.class);
                    cmpid=user.getCmpid();
                    eid=user.getEid();

                    drinternall = FirebaseDatabase.getInstance().getReference().child("Internships").child( cmpid );
                    drinternall.keepSynced(true);

                    optionsinternall = new FirebaseRecyclerOptions.Builder<internall_md>().setQuery(drinternall,internall_md.class).build();

                    adapterinternall = new FirebaseRecyclerAdapter<internall_md, internall_vh>(optionsinternall) {
                        @Override
                        protected void onBindViewHolder(@NonNull internall_vh holder, int position, @NonNull final internall_md model) {
                            holder.cmpname.setText(model.getIntname());
                            holder.cmpsubname.setText(model.getCmpname());
                            Picasso.get().load(model.getIntimguri()).into(holder.cmpimg);
                            holder.location.setText(model.getLocation());
                            holder.amount.setText(model.getAmount());
                            holder.duration.setText(model.getDuration());

                            holder.layout_card.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), Application_detail.class);
                                    intent.putExtra("key",model.getKey());
                                    startActivity( intent );
                                }
                            } );

                        }

                        @NonNull
                        @Override
                        public internall_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            return new internall_vh(LayoutInflater.from(view.getContext()).inflate(R.layout.dashboard_vh, parent,false));
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


}
