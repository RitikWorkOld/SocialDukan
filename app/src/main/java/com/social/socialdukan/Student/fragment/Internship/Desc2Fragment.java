package com.social.socialdukan.Student.fragment.Internship;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.social.socialdukan.Student.fragment.Internship.model.internall_md;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.fragment.Internship.model.internall_md;

/**
 * A simple {@link Fragment} subclass.
 */
public class Desc2Fragment extends Fragment {

    TextView textView1,textView2;
    String key;

    public Desc2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate( R.layout.fragment_desc2, container, false );

        textView1 = view.findViewById(R.id.textview1_wca);
        textView2 = view.findViewById(R.id.textview2_wca);

        key = getArguments().getString("key");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Internships");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("id").equalTo(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    internall_md value = dataSnapshot1.getValue(internall_md.class);

                    textView1.setText(value.wca1+"\n");
                    textView2.setText(value.wca2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
