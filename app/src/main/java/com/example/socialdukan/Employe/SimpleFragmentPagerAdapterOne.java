package com.example.socialdukan.Employe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.socialdukan.Employe.Fragment.Applications_Fragment;
import com.example.socialdukan.Employe.Fragment.Hired_Fragment;
import com.example.socialdukan.Employe.Fragment.Rejected_Fragment;
import com.example.socialdukan.Employe.Fragment.Shortlisted_Fragment;
import com.example.socialdukan.Student.fragment.Internship.Desc1Fragment;
import com.example.socialdukan.Student.fragment.Internship.Desc2Fragment;
import com.example.socialdukan.Student.fragment.Internship.DescFragment;

public class SimpleFragmentPagerAdapterOne extends FragmentStatePagerAdapter {


    int tabCount;
    String key;

    //Constructor to the class
    public SimpleFragmentPagerAdapterOne(FragmentManager fm, int tabCount, String key) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.key = key;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Applications_Fragment tab1 = new Applications_Fragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("key",key);
                tab1.setArguments(bundle1);
                return tab1;
            case 1:
                Shortlisted_Fragment tab2 = new Shortlisted_Fragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("key",key);
                tab2.setArguments(bundle2);
                return tab2;
            case 2:
                Hired_Fragment tab3 = new Hired_Fragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("key",key);
                tab3.setArguments(bundle3);
                return tab3;
            case 3:
                Rejected_Fragment tab4 = new Rejected_Fragment();
                Bundle bundle4 = new Bundle();
                bundle4.putString("key",key);
                tab4.setArguments(bundle4);
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }




}