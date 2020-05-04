package com.example.socialdukan.Employe.Login_Register_Employe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.socialdukan.Employe.Fragment.FragmentOne;
import com.example.socialdukan.Employe.Fragment.Fragment_three;
import com.example.socialdukan.Employe.Fragment.Fragment_two;
import com.example.socialdukan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard_emp extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard_emp );
        navigationView=findViewById( R.id.bottom_nav );
        final FragmentOne fragmentOne=new FragmentOne();
        final Fragment_two fragment_two=new Fragment_two();
        final Fragment_three fragment_three=new Fragment_three();
        navigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.one){
                    setFragment( fragmentOne );
                    return true;
                }
                else if(id==R.id.two){
                    setFragment(fragment_two);
                    return true;

                } else if(id==R.id.three){
                    setFragment( fragment_three );
                    return true;
                }

                return false;
            }
        } );
        navigationView.setSelectedItemId( R.id.one );

    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.frame_emp,fragment );
        fragmentTransaction.commit();

    }
}
