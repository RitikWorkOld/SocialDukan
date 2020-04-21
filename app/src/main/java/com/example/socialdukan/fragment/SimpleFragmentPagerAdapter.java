package com.example.socialdukan.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.socialdukan.R;

import org.jetbrains.annotations.NotNull;

public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {


    int tabCount;
    String key;

    //Constructor to the class
    public SimpleFragmentPagerAdapter(FragmentManager fm, int tabCount,String key) {
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
                DescFragment tab1 = new DescFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("key",key);
                tab1.setArguments(bundle1);
                return tab1;
            case 1:
                Desc1Fragment tab2 = new Desc1Fragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("key",key);
                tab2.setArguments(bundle2);
                return tab2;
            case 2:
                Desc2Fragment tab3 = new Desc2Fragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("key",key);
                tab3.setArguments(bundle3);
                return tab3;
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