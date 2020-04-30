package com.example.socialdukan.Student.fragment.profile.aboutus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.other_services.Card2_Form;
import com.example.socialdukan.Student.fragment.other_services.Card2_detail;
import com.example.socialdukan.Student.fragment.other_services.model.card_model;
import com.example.socialdukan.Student.fragment.other_services.model.card_vh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class about_us extends AppCompatActivity {
    RecyclerView rv_internall;
    private ArrayList<aboutus_md> arrayList;
    String TAG = "CARD *****-----";
    DatabaseReference drinternall;
    FirebaseRecyclerOptions<aboutus_md> optionsinternall;
   // String id;
    FirebaseRecyclerAdapter<aboutus_md, aboutus_vh> adapterinternall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_about_us );

        rv_internall = findViewById(R.id.recyclerview);
        rv_internall.setHasFixedSize(true);
        rv_internall.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        drinternall = FirebaseDatabase.getInstance().getReference().child("aboutus").child("features");
        drinternall.keepSynced(true);
        arrayList = new ArrayList<aboutus_md>();
        optionsinternall = new FirebaseRecyclerOptions.Builder<aboutus_md>().setQuery(drinternall,aboutus_md.class).build();
        adapterinternall = new FirebaseRecyclerAdapter<aboutus_md, aboutus_vh>(optionsinternall) {
            @Override
            protected void onBindViewHolder(@NonNull aboutus_vh holder, int position, @NonNull final aboutus_md model) {
                holder.title.setText(model.getTitle());
                Log.d(TAG,"feautrrs");
                Picasso.get().load(model.getIcon()).into(holder.cmpimg);
                holder.number.setText(model.getNumber());
            }

            @NonNull
            @Override
            public aboutus_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new aboutus_vh( LayoutInflater.from( about_us.this).inflate(R.layout.aboutus_vh,parent,false));
            }
        };
        rv_internall.setAdapter(adapterinternall);
        adapterinternall.startListening();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("aboutus").child("admin");
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null){
                    long j = dataSnapshot.getChildrenCount();
                    Toast.makeText(getBaseContext(), "HELLO11", Toast.LENGTH_SHORT).show();
                    int i = (int)j;
                    final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
                    StartProcess(viewPager,i);
                }
                else {
                  Toast.makeText(getBaseContext(), "HELLO", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void StartProcess(ViewPager viewPager,int i){

        Toast.makeText(getBaseContext(), "HELLO13", Toast.LENGTH_SHORT).show();
        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this),i);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
