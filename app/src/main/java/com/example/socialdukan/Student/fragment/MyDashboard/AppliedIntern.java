package com.example.socialdukan.Student.fragment.MyDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.example.socialdukan.Student.Notifications.Notifications;
import com.example.socialdukan.Student.Notifications.Notifications_Dots;
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

import static android.view.View.GONE;

public class AppliedIntern extends Fragment {

    BucketRecyclerView rv_applied_intern;
    FirebaseRecyclerOptions<applied_intern_md> app_intern_options;
    FirebaseRecyclerAdapter<applied_intern_md, applied_intern_vh> app_intern_adapter;
    ImageView notification_btn,notification_badge;
    private View no_app;

    public AppliedIntern(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_applied_intern,container,false);

        rv_applied_intern = view.findViewById(R.id.rv_applied_intern);
        notification_badge = (ImageView)view.findViewById(R.id.notificationbadge);
        no_app=view.findViewById( R.id.no_app );

        notification_badge.setVisibility( GONE);
        DatabaseReference databaseReferencenot = FirebaseDatabase.getInstance().getReference().child("NotificationDots")
                .child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReferencenot.keepSynced(true);
        databaseReferencenot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notifications_Dots notifications_dots = dataSnapshot.getValue(Notifications_Dots.class);
                if (notifications_dots != null)
                {
                    if (notifications_dots.getDotstatus().equals("yes")){
                        notification_badge.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        rv_applied_intern.setHasFixedSize(true);
        rv_applied_intern.setLayoutManager(new LinearLayoutManager(container.getContext()));
        rv_applied_intern.showIfEmpty( no_app );

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
                db.orderByChild("id").equalTo(model.getInternid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            internall_md value = dataSnapshot1.getValue(internall_md.class);

                            holder.intern_name.setText(value.getIntname());
                            holder.company_name.setText(value.getCmpname());
                            Picasso.get().load(value.getIntimguri()).into(holder.intern_img);

                            notification_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(getActivity(), Notifications.class);

                                    startActivity(intent);

                                }
                            });


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
                        intent.putExtra( "internid",model.getInternid() );
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
