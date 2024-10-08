package com.social.socialdukan.Student.Login_Register_Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.socialdukan.R;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.social.socialdukan.Student.fragment.Event.EventFragment;
import com.social.socialdukan.Student.fragment.MyDashboard.AppliedIntern;
import com.social.socialdukan.Student.fragment.Internship.InternFragment;
import com.social.socialdukan.Student.fragment.other_services.Other_Services;
import com.social.socialdukan.Student.fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private Toast backToast;
    BottomNavigationView navigationView;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.d(TAG, "onCreate: Dashboard activity started");

        navigationView = findViewById(R.id.bottom_nav);

        if (!haveNetworkConnection()) {
            Toast.makeText(Dashboard.this, "No Network Connection", Toast.LENGTH_LONG).show();
            Log.d(TAG, "onCreate: No network connection available");
        }

        final InternFragment intern = new InternFragment();
        final ProfileFragment profile = new ProfileFragment();
        final Other_Services other_services = new Other_Services();
        final AppliedIntern appliedIntern = new AppliedIntern();
        final EventFragment eventFragment = new EventFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Log.d(TAG, "onNavigationItemSelected: Selected item ID: " + id);

                switch (id) {
                    case R.id.intern:
                        setFragment(intern);
                        Log.d(TAG, "onNavigationItemSelected: Intern fragment selected");
                        return true;
                    case R.id.profile:
                        setFragment(profile);
                        Log.d(TAG, "onNavigationItemSelected: Profile fragment selected");
                        return true;
                    case R.id.other_serv:
                        setFragment(other_services);
                        Log.d(TAG, "onNavigationItemSelected: Other Services fragment selected");
                        return true;
                    case R.id.bot:
                        setFragment(eventFragment);
                        Log.d(TAG, "onNavigationItemSelected: Event fragment selected");
                        return true;
                    case R.id.chat_box:
                        setFragment(appliedIntern);
                        Log.d(TAG, "onNavigationItemSelected: Applied Intern fragment selected");
                        return true;
                    default:
                        return false;
                }
            }
        });

        navigationView.setSelectedItemId(R.id.intern);
        Log.d(TAG, "onCreate: Default fragment (Intern) set");

        if (isFirstTime()) {
            Log.d(TAG, "onCreate: First time user detected, showing TapTargetSequence");
            new TapTargetSequence(this).targets(
                    TapTarget.forView(findViewById(R.id.intern), "Internship button", "Check for top internship opportunities and apply!! \n\n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .cancelable(false)
                            .id(1),
                    TapTarget.forView(findViewById(R.id.other_serv), "Miscellaneous", "Fill the form to connect with the team for raising sponsorships, web & app development, merchandise & a lot more!!\n\n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(2),
                    TapTarget.forView(findViewById(R.id.bot), "Check for events", "Find some top events happening and apply with your choice! \n\n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .cancelable(false)
                            .id(3),
                    TapTarget.forView(findViewById(R.id.chat_box), "Dashboard", "Check the status of your internships & campaigns applied.\n\n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(4),
                    TapTarget.forView(findViewById(R.id.profile), "Profile", "Check your profile and update from here if required.\n\n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(5)
            ).listener(new TapTargetSequence.Listener() {
                @Override
                public void onSequenceFinish() {
                    Log.d(TAG, "TapTargetSequence: Sequence finished");
                }

                @Override
                public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                    Log.d(TAG, "TapTargetSequence: Step completed, target ID: " + lastTarget.id());
                }

                @Override
                public void onSequenceCanceled(TapTarget lastTarget) {
                    Log.d(TAG, "TapTargetSequence: Sequence canceled, target ID: " + lastTarget.id());
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            Log.d(TAG, "onBackPressed: Back pressed twice, finishing activity");
            finish();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
            Log.d(TAG, "onBackPressed: Back pressed once, showing toast");
        }
        backPressedTime = System.currentTimeMillis();
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        Log.d(TAG, "haveNetworkConnection: Wifi connected: " + haveConnectedWifi + ", Mobile connected: " + haveConnectedMobile);
        return haveConnectedWifi || haveConnectedMobile;
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        Log.d(TAG, "isFirstTime: Ran before: " + ranBefore);
        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
            Log.d(TAG, "isFirstTime: First time, updating preferences");
        }
        return !ranBefore;
    }

    private synchronized void setFragment(Fragment fragment) {
        Log.d(TAG, "setFragment: Setting fragment " + fragment.getClass().getSimpleName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commitNow();
        Log.d(TAG, "setFragment: Fragment set");
    }
}
