package com.example.socialdukan.Student.fragment.MyDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Notifications;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AppliedIntern extends Fragment {

    RecyclerView rv_applied_intern;
    FirebaseRecyclerOptions<applied_intern_md> app_intern_options;
    FirebaseRecyclerAdapter<applied_intern_md, applied_intern_vh> app_intern_adapter;
    ImageView notification_btn;
    public AppliedIntern(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_applied_intern,container,false);

        rv_applied_intern = view.findViewById(R.id.rv_applied_intern);
        rv_applied_intern.setHasFixedSize(true);
        rv_applied_intern.setLayoutManager(new LinearLayoutManager(container.getContext()));
        notification_btn = (ImageView) view.findViewById(R.id.iv_notification_btn);
        DatabaseReference db_applied_intern = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db_applied_intern.keepSynced(true);

        app_intern_options = new FirebaseRecyclerOptions.Builder<applied_intern_md>().setQuery(db_applied_intern,applied_intern_md.class).build();

        app_intern_adapter = new FirebaseRecyclerAdapter<applied_intern_md, applied_intern_vh>(app_intern_options) {
            @Override
            protected void onBindViewHolder(@NonNull final applied_intern_vh holder, int position, @NonNull final applied_intern_md model) {

                holder.intern_status.setText(model.getStatus());
                if(model.getStatus().equals("Applied")){
                    holder.intern_status.setBackground( getResources().getDrawable( R.drawable.applied ) );

                }
                if(model.getStatus().equals("Hired")){
                    holder.intern_status.setBackground( getResources().getDrawable( R.drawable.info_btn ) );

                }
                if(model.getStatus().equals("In-touch")){
                    holder.intern_status.setBackground( getResources().getDrawable( R.drawable.intouch ) );

                }
                if(model.getStatus().equals("Position Filled")){
                    holder.intern_status.setBackground( getResources().getDrawable( R.drawable.positionfilled ) );

                }
                if(model.getStatus().equals("Not Selected")){
                    holder.intern_status.setBackground( getResources().getDrawable( R.drawable.positionfilled ) );

                }

                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Internships");
                db.keepSynced(true);
                db.orderByChild("key").equalTo(model.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            internall_md value = dataSnapshot1.getValue(internall_md.class);
                            notification_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(getActivity(), Notifications.class);
                                    startActivity(intent);

                                }
                            });
                            holder.intern_name.setText(value.getIntname());
                            holder.company_name.setText(value.getCmpname());
                            Picasso.get().load(value.getIntimguri()).into(holder.intern_img);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                holder.view_app_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(view.getContext(), InternForm.class);
                        intent.putExtra("key",model.getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public applied_intern_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new applied_intern_vh(LayoutInflater.from(view.getContext()).inflate(R.layout.applied_intern_card,parent,false));
            }
        };

        rv_applied_intern.setAdapter(app_intern_adapter);
        app_intern_adapter.startListening();

        return view;
    }
}
