package com.social.socialdukan.Student.fragment.Event.FAQ;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;


public class Faq_viewholder extends RecyclerView.ViewHolder {

    public TextView question;
    public TextView answer;

    public Faq_viewholder(@NonNull View itemView) {
        super( itemView );

        question = itemView.findViewById( R.id.faq_ques );
        answer = itemView.findViewById( R.id.faq_ans );
    }
}
