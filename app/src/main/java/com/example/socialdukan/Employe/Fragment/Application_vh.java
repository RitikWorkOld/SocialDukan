package com.example.socialdukan.Employe.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class Application_vh extends RecyclerView.ViewHolder {

    public   ImageView cmpimg;
    public   TextView cmpname;
    public  TextView cmpsubname;

    public  RelativeLayout layout_card;

    public Application_vh(@NonNull View itemView) {
        super(itemView);

        cmpimg = itemView.findViewById(R.id.icd_cmpimg);
        cmpname = itemView.findViewById(R.id.icd_cmpname);
        layout_card=itemView.findViewById( R.id.layout_card );
        cmpsubname = itemView.findViewById(R.id.icd_cmpsubname);

    }
}