package com.social.socialdukan.Student.fragment.profile.aboutus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class CardFragment extends Fragment {

    private CardView cardView;
    String TAG = "CARD *****-----";


    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = (CardView) view.findViewById( R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);





        String position = String.format("%d", getArguments().getInt("position"));


        //final TextView title = (TextView) view.findViewById(R.id.title_speak);
        //final TextView description = (TextView)view.findViewById(R.id.desc_speak);

        final TextView title = (TextView) view.findViewById(R.id.title_speak);
        final TextView description = (TextView)view.findViewById(R.id.desc_speak);
        final ImageView image_p = view.findViewById(R.id.image_speaker);
       // final ImageView insta = view.findViewById( R.id.insta );
       // final ImageView mail = view.findViewById( R.id.gmail );

        Log.d(TAG,"came out ----------------------------------------------------------");

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("aboutus").child("admin");
        databaseReference1.orderByChild("id").equalTo(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Fall in firebase---------------------------------------------------");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.d(TAG,"Fall in firebase snapshot ---------------------------------------------------");
                    final Speaker_detail speaker_detail = dataSnapshot1.getValue(Speaker_detail.class);
                    String name = speaker_detail.getName();
                    String desc = speaker_detail.getDesc();
                    String imguri = speaker_detail.getImguri();

                    title.setText(name);
                    description.setText(desc);
                    Picasso.get().load(imguri).into(image_p);
        /*   insta.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse(speaker_detail.getInsta());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
} );*/
          /*  mail.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
                String mail= speaker_detail.getMail();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra( Intent.EXTRA_EMAIL,mail );
                intent.setType( "message/rfc822" );

                startActivity( Intent.createChooser( intent,"Choose an email client" ) );
    }
} );*/


                    Log.d(TAG,"Here is name ----------------------------------------  "+name);
                    Log.d(TAG,"Here is desc ----------------------------------------  "+desc);
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
