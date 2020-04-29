package com.example.socialdukan.Student.fragment.MyDashboard;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class applied_intern_vh extends RecyclerView.ViewHolder {

    public TextView intern_name;
    public TextView company_name;
    public TextView intern_status;
    public TextView view_app_btn;
    public CircleImageView intern_img;

    public applied_intern_vh(@NonNull View itemView) {
        super(itemView);

        intern_name = itemView.findViewById(R.id.intern_name);
        intern_status = itemView.findViewById(R.id.intern_status);
        company_name = itemView.findViewById(R.id.company_name);
        view_app_btn = itemView.findViewById(R.id.view_app_btn);
        intern_img = itemView.findViewById(R.id.intern_img);
    }
}
