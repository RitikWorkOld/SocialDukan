package com.social.socialdukan.Student.ModelandViewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class addexp1_viewholder extends RecyclerView.ViewHolder {

    public TextView companynamelayout,show;

    public ImageView cancelbtn;
    public TextView editexp;
    public boolean expand;

    public addexp1_viewholder(@NonNull View itemView) {
        super(itemView);

        expand = false;
        editexp = itemView.findViewById( R.id.editexp);
        companynamelayout = itemView.findViewById(R.id.companyname_layout);
        show=itemView.findViewById( R.id.show );
        cancelbtn = itemView.findViewById(R.id.cancel_btn);

    }
}
