package com.example.socialdukan.Student.fragment.profile.aboutus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.socialdukan.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.squareup.picasso.Picasso;

public class CardFragmentOne extends Fragment {

    private CardView cardView;
    String TAG = "CARD *****-----";


    public static Fragment getInstance(int position) {
        CardFragmentOne f = new CardFragmentOne();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_testimonial_vh, container, false);

        cardView = (CardView) view.findViewById( R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapterOne.MAX_ELEVATION_FACTOR);


        final String position = String.format("%d", getArguments().getInt("position"));



        //final TextView title = (TextView) view.findViewById(R.id.title_speak);
        //final TextView description = (TextView)view.findViewById(R.id.desc_speak);
        final TextView posit = (TextView) view.findViewById(R.id.position);
        final TextView working = (TextView)view.findViewById(R.id.work);
        final TextView title = (TextView) view.findViewById(R.id.name);
        final TextView description = (TextView)view.findViewById(R.id.description);
        final de.hdodenhof.circleimageview.CircleImageView image_p = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.photo);

        Log.d(TAG,"came out ----------------------------------------------------------");

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("aboutus").child("testimonial");
        databaseReference1.orderByChild("id").equalTo(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Fall in firebase12--------------------------------------------------");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.d(TAG,"Fall in firebase snapshot 12---------------------------------------------------");
                    aboutus_md about = dataSnapshot1.getValue(aboutus_md.class);
                    String name = about.getName();
                    String desc = about.getDescription();
                    String imguri = about.getPhoto();
                    String pos = about.getPosition();
                    String work = about.getWork();

                    title.setText(name);
                    description.setText(desc);
                    Picasso.get().load(imguri).into(image_p);
                    posit.setText(pos);
                    working.setText(work);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
