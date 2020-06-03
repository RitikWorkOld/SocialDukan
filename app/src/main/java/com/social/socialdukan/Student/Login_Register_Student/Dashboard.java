package com.social.socialdukan.Student.Login_Register_Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.socialdukan.R;

import com.social.socialdukan.Student.fragment.Event.EventFragment;
import com.social.socialdukan.Student.fragment.MyDashboard.AppliedIntern;
import com.social.socialdukan.Student.fragment.Internship.InternFragment;
import com.social.socialdukan.Student.fragment.other_services.Other_Services;
import com.social.socialdukan.Student.fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Dashboard extends AppCompatActivity {
    private Toast backToast;
    BottomNavigationView navigationView;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_dashboard);
        navigationView=findViewById( R.id.bottom_nav );
        if(!haveNetworkConnection()){
            Toast.makeText(Dashboard.this,"No Network Connection",Toast.LENGTH_LONG).show();
        }
        final InternFragment intern=new InternFragment();
        final ProfileFragment profile=new ProfileFragment();
        final Other_Services other_services=new Other_Services();
        final AppliedIntern appliedIntern = new AppliedIntern();
        final EventFragment eventFragment=new EventFragment();

       navigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.intern){

                    setFragment( intern );
                    return true;
                }
                else if(id==R.id.profile){
                    setFragment(profile);
                    return true;

                } else if(id==R.id.other_serv){
                    setFragment( other_services );
                    return true;
                }
                else if(id==R.id.bot){
                    setFragment( eventFragment );
                    return true;

                } else if(id==R.id.chat_box){
                    setFragment(  appliedIntern);
                    return true;

                }
                return false;
            }
        } );
        navigationView.setSelectedItemId( R.id.intern );


    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finish();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

   private synchronized void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.frame,fragment );

        fragmentTransaction.commitNow();

    }
}
