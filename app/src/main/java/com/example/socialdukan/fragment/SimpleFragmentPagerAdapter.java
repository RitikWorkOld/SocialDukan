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

    //Constructor to the class
    public SimpleFragmentPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                DescFragment tab1 = new DescFragment();
                return tab1;
            case 1:
                Desc1Fragment tab2 = new Desc1Fragment();
                return tab2;
            case 2:
                Desc2Fragment tab3 = new Desc2Fragment();
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