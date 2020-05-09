package com.example.socialdukan.Student.Login_Register_Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.socialdukan.R;
import com.example.socialdukan.Student.Chat_bot.feature.MainActivity;
import com.example.socialdukan.Student.fragment.Event.EventFragment;
import com.example.socialdukan.Student.fragment.MyDashboard.AppliedIntern;
import com.example.socialdukan.Student.fragment.Internship.InternFragment;
import com.example.socialdukan.Student.fragment.other_services.Other_Services;
import com.example.socialdukan.Student.fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Dashboard extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_dashboard);
        navigationView=findViewById( R.id.bottom_nav );

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
   private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.frame,fragment );
        fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );
        fragmentTransaction.commit();

    }
}
