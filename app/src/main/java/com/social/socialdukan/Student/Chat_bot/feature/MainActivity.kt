package com.social.socialdukan.Student.Chat_bot.feature

import ai.api.AIConfiguration
import ai.api.AIDataService
import ai.api.android.AIService
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.socialdukan.R
import com.social.socialdukan.Student.Chat_bot.adapter.ChatAdapter
import com.social.socialdukan.Student.Chat_bot.entity.ChatMessage
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat_bot.*


class MainActivity : AppCompatActivity(),MainContract.View {

    lateinit var adapter: ChatAdapter
    lateinit var ref: DatabaseReference
    lateinit var aiService: AIService
    lateinit var aiDataAIService: AIDataService
    var user: String? = null
    var key: String? = null
    var notiid: String? = null

    lateinit var mPresenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
    notiid = FirebaseDatabase.getInstance().reference.child("chat").push().key
        initPresenter()

        rvChat.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        rvChat.layoutManager = layoutManager

        ref = notiid?.let { FirebaseDatabase.getInstance().reference.child("chat").child(it) }!!
        ref.keepSynced(true)

        btnSend.setOnClickListener {
            val message = edChat.text.toString()
            if(message.isNullOrBlank()){
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            }
           else if (message != "") {
                mPresenter.sendMessage(message)
            } else {
                aiService.startListening()
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            }
            edChat.setText("")
        }

        val options = FirebaseRecyclerOptions.Builder<ChatMessage>()
                .setQuery(ref.child("chat"), ChatMessage::class.java)
                .build()

        adapter = ChatAdapter(options)

        rvChat.adapter = adapter

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                val msgCount = adapter.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition == -1 || positionStart >= msgCount - 1 && lastVisiblePosition == positionStart - 1) {
                    rvChat.scrollToPosition(positionStart)
                }
            }
        })

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
        mPresenter.onDestroy()
    }

    fun initPresenter(){
        val aiConfiguration = ai.api.android.AIConfiguration("81903d97e9b6421a83f67e3b6f49ba22",
                AIConfiguration.SupportedLanguages.English,
                ai.api.android.AIConfiguration.RecognitionEngine.System)

        aiService = AIService.getService(this, aiConfiguration)
        aiDataAIService = AIDataService(aiConfiguration)

        ref = notiid?.let { FirebaseDatabase.getInstance().reference.child("chat").child(it) }!!

        mPresenter = MainPresenter(aiDataAIService, ref)

    }
}