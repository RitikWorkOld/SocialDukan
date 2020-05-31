package com.social.socialdukan.Student.Notifications;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;


public class Notification_ViewHolder extends RecyclerView.ViewHolder {

    TextView Title;
    TextView Description;
    TextView Date;
    //ImageView Cancel_btn;
    LinearLayout notification_trigger;

    public Notification_ViewHolder(@NonNull View itemView) {
        super(itemView);

        Title = itemView.findViewById( R.id.tv_noti_title);
        Description = itemView.findViewById(R.id.tv_noti_desc);
        Date=itemView.findViewById( R.id.date );
        //Cancel_btn = itemView.findViewById(R.id.cancel_noti_btn);
        notification_trigger = itemView.findViewById(R.id.notification_trigger);
    }
}
