package com.example.socialdukan.Employe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialdukan.Employe.Login_Register_Employe.Reg_Employe;
import com.example.socialdukan.Student.Chat_bot.feature.MainActivity;
import com.example.socialdukan.Student.Miscellaneous.IntroViewPagerAdapter;
import com.example.socialdukan.Student.Miscellaneous.ScreenItem;
import com.example.socialdukan.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity2 extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openened before or not

     /*if (restorePrefData()) {

            Intent mainActivity = new Intent(getApplicationContext(),Login_Student.class );
            startActivity(mainActivity);
            finish();


        }*/

        setContentView( R.layout.activity_intro2);

        // hide the action bar

        //getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);


        // fill list screen

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Signup - Create account - Post internship!!","",R.drawable.postinternships));
        mList.add(new ScreenItem("Easily get top influencers across country for your brand promotion.","",R.drawable.hire_influencers));
        mList.add(new ScreenItem("Well dedicated team to manage your internship and campaigns","",R.drawable.dedicatedteam));
        mList.add(new ScreenItem("Hire top interns across the globe!\nEasy access!! Easy handling!!","",R.drawable.top_interns));


        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }

                if (position == mList.size()-1) { // when we rech to the last screen

                    //show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }



            }
        });

        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(), Reg_Employe.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                // savePrefsData();
                finish();



            }
        });

        // skip button click listener





    }

 /*   private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }*/

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);

        tabIndicator.setVisibility(View.INVISIBLE);
        // ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }
}
