package com.example.socialdukan.Student.fragment.Internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.SimpleFragmentPagerAdapter;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InternDetail extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    DatabaseReference databaseReferencedetail;
    String key;
    ImageView cmpimage;
    TextView intername,cmpname,location,stipend,duration,worktime;
    Button apply_btn,applied_btn;
    TextView ctext1,ctext2,ctext3;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dbref.keepSynced(true);
        dbref.orderByChild("key").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    apply_btn.setVisibility(View.GONE);
                    applied_btn.setVisibility(View.VISIBLE);
                }
                else {
                    apply_btn.setVisibility(View.VISIBLE);
                    applied_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intern_detail );

        apply_btn = findViewById(R.id.applied);
        applied_btn = findViewById(R.id.alr_applied);

        cmpimage = findViewById(R.id.icd_cmp_img);
        intername = findViewById(R.id.cmp_work_detail);
        cmpname = findViewById(R.id.cmp_name_detail);
        location = findViewById(R.id.icd_location);
        stipend = findViewById(R.id.stipend_detail);
        duration = findViewById(R.id.icd_duration);
        worktime = findViewById(R.id.work_details);
        ctext1 = findViewById(R.id.title1);
        ctext2 = findViewById(R.id.title2);
        ctext3 = findViewById(R.id.title3);

        key = getIntent().getStringExtra("key");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("key","hello");
        DescFragment descFragment = new DescFragment();
        descFragment.setArguments(bundle);
        Desc1Fragment desc1Fragment = new Desc1Fragment();
        desc1Fragment.setArguments(bundle);
        Desc2Fragment desc2Fragment = new Desc2Fragment();
        desc2Fragment.setArguments(bundle);

        fragmentTransaction.commit();

        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("Internships");
        databaseReferencedetail.keepSynced(true);
        databaseReferencedetail.orderByChild("key").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    internall_md valueintern = dataSnapshot1.getValue(internall_md.class);

                    Picasso.get().load(valueintern.intimguri).into(cmpimage);
                    intername.setText(valueintern.intname);
                    cmpname.setText(valueintern.cmpname);
                    location.setText(valueintern.location);
                    stipend.setText(valueintern.amount);
                    duration.setText(valueintern.duration);
                    worktime.setText(valueintern.worktime);
                    if (valueintern.getCtext1().equals("no")){
                        ctext1.setPaintFlags(ctext1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    else if (valueintern.getCtext2().equals("no")){
                        ctext2.setPaintFlags(ctext2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    else if (valueintern.getCtext3().equals("no")){
                        ctext3.setPaintFlags(ctext3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InternDetail.this, ApplyIntern.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });


//Initializing the tablayout
        tabLayout = (TabLayout) findViewById( R.id.sliding_tab );

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Description));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Description1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Description2));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Creating our pager adapter
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),key);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
