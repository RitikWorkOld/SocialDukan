package com.example.socialdukan.Student.fragment.Event;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

public class events_vh extends RecyclerView.ViewHolder {

    public TextView Title;
    public TextView Descp;
    public ImageView Mimguri;
    public TextView read_more_ebtn;
    public events_vh(@NonNull View itemView) {
        super(itemView);

        Title = itemView.findViewById(R.id.event_title);
        Descp = itemView.findViewById(R.id.event_descp);
        Mimguri = itemView.findViewById(R.id.event_main_img);
        read_more_ebtn = itemView.findViewById(R.id.read_more_events);
    }
}