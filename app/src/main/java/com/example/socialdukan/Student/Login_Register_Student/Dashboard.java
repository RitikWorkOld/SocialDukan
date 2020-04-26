package com.example.socialdukan.Student.Login_Register_Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.socialdukan.R;
import com.example.socialdukan.Student.feature.MainActivity;
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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity( intent );
                    return true;

                } else if(id==R.id.chat_box){
                    setFragment(  profile);
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
        fragmentTransaction.commit();

    }




}
