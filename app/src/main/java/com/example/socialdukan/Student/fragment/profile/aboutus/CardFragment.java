package com.example.socialdukan.Student.fragment.profile.aboutus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private LinearLayout linearLayout1;
    private TextView title_1,desc_1;

    private LinearLayout linearLayout2;
    private TextView title_2,desc_2;

    private LinearLayout linearLayout3;
    private TextView title_3,desc_3;

    private LinearLayout linearLayout4;
    private TextView title_4,desc_4;

    private LinearLayout linearLayout5;
    private TextView title_5,desc_5;

    private LinearLayout linearLayout6;
    private TextView title_6,desc_6;

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

        linearLayout1 = (LinearLayout)view.findViewById(R.id.card1);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.card2);
        linearLayout3 = (LinearLayout)view.findViewById(R.id.card3);
        linearLayout4 = (LinearLayout)view.findViewById(R.id.card4);
        linearLayout5 = (LinearLayout)view.findViewById(R.id.card5);
        linearLayout6 = (LinearLayout)view.findViewById(R.id.card6);

        title_1 = (TextView)view.findViewById(R.id.speaker_title_details_1);
        desc_1 = (TextView)view.findViewById(R.id.speaker_desc_detail_1);
        title_2 = (TextView)view.findViewById(R.id.speaker_title_details_2);
        desc_2 = (TextView)view.findViewById(R.id.speaker_desc_detail_2);
        title_3 = (TextView)view.findViewById(R.id.speaker_title_details_3);
        desc_3 = (TextView)view.findViewById(R.id.speaker_desc_detail_3);
        title_4 = (TextView)view.findViewById(R.id.speaker_title_details_4);
        desc_4 = (TextView)view.findViewById(R.id.speaker_desc_detail_4);
        title_5 = (TextView)view.findViewById(R.id.speaker_title_details_5);
        desc_5 = (TextView)view.findViewById(R.id.speaker_desc_detail_5);
        title_6 = (TextView)view.findViewById(R.id.speaker_title_details_6);
        desc_6 = (TextView)view.findViewById(R.id.speaker_desc_detail_6);

        String position = String.format("%d", getArguments().getInt("position"));

        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("speakers").child(position);
        databaseReference2.keepSynced(true);
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String count = String.valueOf(dataSnapshot.getChildrenCount()-4);
                //Toast.makeText(view.getContext(),"COUNT = "+count,Toast.LENGTH_SHORT).show();

                switch (count){
                    case "2" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        break;
                    case "4" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        linearLayout2.setVisibility( View.VISIBLE);
                        break;
                    case "6" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        linearLayout2.setVisibility( View.VISIBLE);
                        linearLayout3.setVisibility( View.VISIBLE);
                        break;
                    case "8" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        linearLayout2.setVisibility( View.VISIBLE);
                        linearLayout3.setVisibility( View.VISIBLE);
                        linearLayout4.setVisibility( View.VISIBLE);
                        break;
                    case "10" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        linearLayout2.setVisibility( View.VISIBLE);
                        linearLayout3.setVisibility( View.VISIBLE);
                        linearLayout4.setVisibility( View.VISIBLE);
                        linearLayout5.setVisibility( View.VISIBLE);
                        break;
                    case "12" :
                        linearLayout1.setVisibility( View.VISIBLE);
                        linearLayout2.setVisibility( View.VISIBLE);
                        linearLayout3.setVisibility( View.VISIBLE);
                        linearLayout4.setVisibility( View.VISIBLE);
                        linearLayout5.setVisibility( View.VISIBLE);
                        linearLayout6.setVisibility( View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //final TextView title = (TextView) view.findViewById(R.id.title_speak);
        //final TextView description = (TextView)view.findViewById(R.id.desc_speak);

        final TextView title = (TextView) view.findViewById(R.id.title_speak);
        final TextView description = (TextView)view.findViewById(R.id.desc_speak);
        final de.hdodenhof.circleimageview.CircleImageView image_p = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.image_speaker);

        Log.d(TAG,"came out ----------------------------------------------------------");

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("speakers");
        databaseReference1.orderByChild("id").equalTo(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Fall in firebase---------------------------------------------------");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.d(TAG,"Fall in firebase snapshot ---------------------------------------------------");
                    Speaker_detail speaker_detail = dataSnapshot1.getValue(Speaker_detail.class);
                    String name = speaker_detail.getName();
                    String desc = speaker_detail.getDesc();
                    String imguri = speaker_detail.getImguri();

                    title.setText(name);
                    description.setText(desc);
                    Picasso.get().load(imguri).into(image_p);

                    title_1.setText(speaker_detail.getT1());
                    desc_1.setText(speaker_detail.getD1());

                    title_2.setText(speaker_detail.getT2());
                    desc_2.setText(speaker_detail.getD2());

                    title_3.setText(speaker_detail.getT3());
                    desc_3.setText(speaker_detail.getD3());

                    title_4.setText(speaker_detail.getT4());
                    desc_4.setText(speaker_detail.getD4());

                    title_5.setText(speaker_detail.getT5());
                    desc_5.setText(speaker_detail.getD5());

                    title_6.setText(speaker_detail.getT6());
                    desc_6.setText(speaker_detail.getD6());

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
