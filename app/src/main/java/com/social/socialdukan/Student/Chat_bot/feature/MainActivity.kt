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
import com.example.socialdukan.databinding.ActivityChatBotBinding
import com.social.socialdukan.Student.Chat_bot.adapter.ChatAdapter
import com.social.socialdukan.Student.Chat_bot.entity.ChatMessage
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityChatBotBinding
    lateinit var adapter: ChatAdapter
    lateinit var ref: DatabaseReference
    lateinit var aiService: AIService
    lateinit var aiDataAIService: AIDataService
    var user: String? = null
    var key: String? = null
    var notiid: String? = null

    lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityChatBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notiid = FirebaseDatabase.getInstance().reference.child("chat").push().key
        initPresenter()

        // Setup RecyclerView
        binding.rvChat.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        binding.rvChat.layoutManager = layoutManager

        ref = notiid?.let { FirebaseDatabase.getInstance().reference.child("chat").child(it) }!!
        ref.keepSynced(true)

        binding.btnSend.setOnClickListener {
            val message = binding.edChat.text.toString()
            if (message.isNullOrBlank()) {
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            } else if (message != "") {
                mPresenter.sendMessage(message)
            } else {
                aiService.startListening()
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            }
            binding.edChat.setText("")
        }

        val options = FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(ref.child("chat"), ChatMessage::class.java)
            .build()

        adapter = ChatAdapter(options)

        binding.rvChat.adapter = adapter

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                val msgCount = adapter.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition == -1 || positionStart >= msgCount - 1 && lastVisiblePosition == positionStart - 1) {
                    binding.rvChat.scrollToPosition(positionStart)
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

    private fun initPresenter() {
        val aiConfiguration = ai.api.android.AIConfiguration(
            "81903d97e9b6421a83f67e3b6f49ba22",
            AIConfiguration.SupportedLanguages.English,
            ai.api.android.AIConfiguration.RecognitionEngine.System
        )

        aiService = AIService.getService(this, aiConfiguration)
        aiDataAIService = AIDataService(aiConfiguration)

        ref = notiid?.let { FirebaseDatabase.getInstance().reference.child("chat").child(it) }!!

        mPresenter = MainPresenter(aiDataAIService, ref)
    }
}
