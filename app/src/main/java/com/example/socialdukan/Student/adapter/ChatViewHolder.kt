package com.example.socialdukan.Student.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialdukan.R


/**
 * Created by muhrahmatullah on 09/10/18.
 */
class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userText : TextView = itemView.findViewById(R.id.userText) as TextView
    var botText  :TextView = itemView.findViewById(R.id.botText) as TextView

}