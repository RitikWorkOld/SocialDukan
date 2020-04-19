package com.example.socialdukan;

import android.telecom.TelecomManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addexp_viewholder extends RecyclerView.ViewHolder {

    TextView companynamelayout;
    TextView companystartlayout;
    TextView companyendlayout;
    TextView companyrolelayout;
    TextView companybenefitslayout;
    TextView editexp;
    ImageView cancelbtn;
    boolean expand;

    public addexp_viewholder(@NonNull View itemView) {
        super(itemView);

        expand = false;
        editexp = itemView.findViewById(R.id.editexp);
        cancelbtn = itemView.findViewById(R.id.cancel_btn);
        companynamelayout = itemView.findViewById(R.id.companyname_layout);
        companystartlayout = itemView.findViewById(R.id.companystartdate_layout);
        companyendlayout = itemView.findViewById(R.id.companyenddate_layout);
        companyrolelayout = itemView.findViewById(R.id.companyrole_layout);
        companybenefitslayout = itemView.findViewById(R.id.companybenefits_layout);

    }
}
