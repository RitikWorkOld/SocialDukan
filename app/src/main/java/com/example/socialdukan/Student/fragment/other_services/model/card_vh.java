package com.example.socialdukan.Student.fragment.other_services.model;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class card_vh extends RecyclerView.ViewHolder {

    public   ImageView cmpimg;
    public   TextView infl_name;
    public  TextView category;
    public  TextView location;
    public  TextView date;
    public Button detail;
    public Button apply;
    public  RelativeLayout layout_card;

    public card_vh(@NonNull View itemView) {
        super(itemView);

        cmpimg = itemView.findViewById(R.id.icd_cmpimg);
        infl_name = itemView.findViewById(R.id.infl_name);
        layout_card=itemView.findViewById( R.id.layout_card );
        category = itemView.findViewById(R.id.categ_name);
        location = itemView.findViewById(R.id.location);
        date = itemView.findViewById(R.id.date);
        detail = itemView.findViewById(R.id.detail_btn);
        apply = itemView.findViewById(R.id.apply_btn);
    }
}
