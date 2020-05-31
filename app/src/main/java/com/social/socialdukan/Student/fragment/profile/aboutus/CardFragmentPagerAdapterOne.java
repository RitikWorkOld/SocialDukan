package com.social.socialdukan.Student.fragment.profile.aboutus;

import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapterOne extends FragmentStatePagerAdapter implements CardAdapterOne {

    private List<CardFragmentOne> fragments;
    private float baseElevation;

    public CardFragmentPagerAdapterOne(final FragmentManager fm, float baseElevation, int x) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;

        for(int i = 0; i< x; i++){
            addCardFragment(new CardFragmentOne());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragmentOne.getInstance(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragmentOne) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragmentOne fragment) {
        fragments.add(fragment);
    }
}
