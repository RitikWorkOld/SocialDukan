package com.example.socialdukan;

import android.telecom.TelecomManager;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addexp1_viewholder extends RecyclerView.ViewHolder {

    TextView companynamelayout,show;


    TextView editexp;
    boolean expand;

    public addexp1_viewholder(@NonNull View itemView) {
        super(itemView);

        expand = false;
        editexp = itemView.findViewById(R.id.editexp);
        companynamelayout = itemView.findViewById(R.id.companyname_layout);
        show=itemView.findViewById( R.id.show );

    }
}
