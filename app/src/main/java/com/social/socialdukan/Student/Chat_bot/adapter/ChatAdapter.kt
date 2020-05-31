package com.social.socialdukan.Student.Chat_bot.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.socialdukan.R
import com.social.socialdukan.Student.Chat_bot.entity.ChatMessage
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions



class ChatAdapter(options: FirebaseRecyclerOptions<_root_ide_package_.com.social.socialdukan.Student.Chat_bot.entity.ChatMessage>)
    : FirebaseRecyclerAdapter<_root_ide_package_.com.social.socialdukan.Student.Chat_bot.entity.ChatMessage, _root_ide_package_.com.social.socialdukan.Student.Chat_bot.adapter.ChatViewHolder>(options) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): _root_ide_package_.com.social.socialdukan.Student.Chat_bot.adapter.ChatViewHolder {
        return _root_ide_package_.com.social.socialdukan.Student.Chat_bot.adapter.ChatViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_chat, p0, false))
    }

    override fun onBindViewHolder(holder: _root_ide_package_.com.social.socialdukan.Student.Chat_bot.adapter.ChatViewHolder, position: Int, model: _root_ide_package_.com.social.socialdukan.Student.Chat_bot.entity.ChatMessage) {
        if (model.user == "user") {
            holder.userText.text = model.chat
            holder.userText.visibility = View.VISIBLE
            holder.botText.visibility = View.GONE

        } else {
            holder.botText.text = model.chat
            holder.botText.visibility = View.VISIBLE
            holder.userText.visibility = View.GONE
        }
    }

}