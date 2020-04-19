package com.example.socialdukan.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class internall_vh extends RecyclerView.ViewHolder {

    ImageView cmpimg;
    TextView cmpname;
    TextView cmpsubname;
    TextView location;
    TextView amount;
    TextView duration;

    public internall_vh(@NonNull View itemView) {
        super(itemView);

        cmpimg = itemView.findViewById(R.id.icd_cmpimg);
        cmpname = itemView.findViewById(R.id.icd_cmpname);
        cmpsubname = itemView.findViewById(R.id.icd_cmpsubname);
        location = itemView.findViewById(R.id.icd_location);
        amount = itemView.findViewById(R.id.icd_amount);
        duration = itemView.findViewById(R.id.icd_duration);
    }
}