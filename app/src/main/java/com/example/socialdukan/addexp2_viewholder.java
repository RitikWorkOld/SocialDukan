package com.example.socialdukan;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addexp2_viewholder extends RecyclerView.ViewHolder {

    TextView companynamelayout,ach_show;


    TextView editexp;
    boolean expand;

    public addexp2_viewholder(@NonNull View itemView) {
        super(itemView);

        expand = false;
        editexp = itemView.findViewById(R.id.editexp);
        companynamelayout = itemView.findViewById(R.id.companyname_layout);
        ach_show = itemView.findViewById(R.id.ach_show);



    }
}
