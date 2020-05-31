package com.social.socialdukan.Student.fragment.profile.aboutus;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class testimonial_vh extends RecyclerView.ViewHolder {
    CircleImageView photo;
    public TextView description;
    public TextView name;
    public TextView position;
    public TextView work;
    public RelativeLayout layout_card;

    public testimonial_vh(@NonNull View itemView) {
        super( itemView );

        photo = itemView.findViewById( R.id.photo );
        description = itemView.findViewById( R.id.description );
        layout_card = itemView.findViewById( R.id.layout_card );
        name = itemView.findViewById( R.id.name );
        position=itemView.findViewById( R.id.position );
        work=itemView.findViewById( R.id.work );



    }
}
