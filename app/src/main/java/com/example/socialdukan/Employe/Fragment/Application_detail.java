package com.example.socialdukan.Employe.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.socialdukan.Employe.SimpleFragmentPagerAdapterOne;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Internship.Desc1Fragment;
import com.example.socialdukan.Student.fragment.Internship.Desc2Fragment;
import com.example.socialdukan.Student.fragment.Internship.DescFragment;
import com.example.socialdukan.Student.fragment.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Application_detail extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_application_detail );
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById( R.id.sliding_tab );
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

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Application"));
        tabLayout.addTab(tabLayout.newTab().setText("Shortlisted"));
        tabLayout.addTab(tabLayout.newTab().setText("Hired"));
        tabLayout.addTab(tabLayout.newTab().setText("Rejected"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Creating our pager adapter
        SimpleFragmentPagerAdapterOne adapter = new SimpleFragmentPagerAdapterOne(getSupportFragmentManager(), tabLayout.getTabCount(),key);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
