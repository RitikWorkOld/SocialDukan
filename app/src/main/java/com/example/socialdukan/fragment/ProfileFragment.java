package com.example.socialdukan.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.socialdukan.Dashboard;
import com.example.socialdukan.Login_Student;
import com.example.socialdukan.R;
import com.example.socialdukan.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

FirebaseAuth mFirebaseAuth;
    public ProfileFragment() {
        // Required empty public constructor
    }

    TextView name_user;
    TextView user_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        final View view = inflater.inflate( R.layout.fragment_profile, container, false );

        name_user = view.findViewById(R.id.name_user);
        user_email = view.findViewById(R.id.user_email);

        final RelativeLayout layout_profile1 = (RelativeLayout)view.findViewById(R.id.pers_detail1);
        final RelativeLayout layout_profile2 = (RelativeLayout)view.findViewById(R.id.pers_detail2);
        final RelativeLayout layout_profile3 = (RelativeLayout)view.findViewById(R.id.pers_detail3);
        final RelativeLayout layout_profile4 = (RelativeLayout)view.findViewById(R.id.pers_detail4);
        final RelativeLayout layout_signout = (RelativeLayout)view.findViewById(R.id.layout_signout);


        //-------------------------------------------------------------------------------------------------
layout_profile1.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Profile_activity1.class);

        startActivity(intent);
    }
} );
        //-------------------------------------------------------------------------------------------------
        layout_profile2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_activity2.class);

                startActivity(intent);
            }
        } );
        //-------------------------------------------------------------------------------------------------
        layout_profile3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_activity3.class);

                startActivity(intent);
            }
        } );
        //-------------------------------------------------------------------------------------------------
        layout_profile4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_activity4.class);

                startActivity(intent);
            }
        } );


        //-------------------------------------------------------------------------------------------------
        layout_signout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) { AlertDialog.Builder builder = new AlertDialog.Builder( Objects.requireNonNull( getActivity() ) );
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.drawable.dukan_w);
                builder.setMessage("Do you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mFirebaseAuth.getInstance().signOut();
                                //saving session

                                Intent intent = new Intent(getActivity(), Login_Student.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
           /* mFirebaseAuth.getInstance().signOut();
                Intent intent = new Intent( view.getContext(), Login_Student.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);*/

            }
        } );
        //-------------------------------------------------------------------------------------------------
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);

                    name_user.setText(user.name);
                    user_email.setText(user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
    //-------------------------------------------------------------------------------------------------


}
