package com.example.socialdukan.Student.fragment.Internship.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class internall_vh extends RecyclerView.ViewHolder {

 public   ImageView cmpimg;
    public   TextView cmpname;
    public  TextView cmpsubname;
    public  TextView location;
    public  TextView amount;
    public  TextView duration;
    public  RelativeLayout layout_card;

    public internall_vh(@NonNull View itemView) {
        super(itemView);

        cmpimg = itemView.findViewById(R.id.icd_cmpimg);
        cmpname = itemView.findViewById(R.id.icd_cmpname);
        layout_card=itemView.findViewById( R.id.layout_card );
        cmpsubname = itemView.findViewById(R.id.icd_cmpsubname);
        location = itemView.findViewById(R.id.icd_location);
        amount = itemView.findViewById(R.id.icd_amount);
        duration = itemView.findViewById(R.id.icd_duration);
    }
}