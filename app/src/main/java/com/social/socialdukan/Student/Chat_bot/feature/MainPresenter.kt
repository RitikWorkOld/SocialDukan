package com.social.socialdukan.Student.Chat_bot.feature

import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.widget.Toast
import com.social.socialdukan.Student.Chat_bot.entity.ChatMessage
import com.social.socialdukan.Student.Login_Register_Student.Login_Student
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.*


/**
 *
 */
class MainPresenter(val aiDataAIService: AIDataService,
                    val ref: DatabaseReference) : MainContract.Presenter {

    val aiRequest = AIRequest()

    val job = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun sendMessage(message: String) {
        val chatMessage = ChatMessage(message, "user")
        ref.child("chat").push().setValue(chatMessage)
        aiRequest.setQuery(message)

        if(message.isNullOrBlank()){

        }
else {
            val response: Deferred<AIResponse>? = coroutineScope.async {
                aiDataAIService.request(aiRequest)
            }

            coroutineScope.launch {
                sendMessageToServer(response?.await())
            }
        }

    }

    private fun sendMessageToServer(response: AIResponse?) {
        val result = response?.result
        val reply = result?.fulfillment?.speech
        val chatMessage = ChatMessage(reply, "bot")
        ref.child("chat").push().setValue(chatMessage)

    }

    override fun onDestroy() {
        job.cancel()
    }
}