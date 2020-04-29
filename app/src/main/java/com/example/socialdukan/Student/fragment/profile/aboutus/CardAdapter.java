package com.example.socialdukan.Student.fragment.profile.aboutus;

import androidx.cardview.widget.CardView;

public interface CardAdapter {

    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
