package com.example.socialdukan.Student.Chat_bot.feature

import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import com.example.socialdukan.Student.Chat_bot.entity.ChatMessage
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.*


/**
 * Created by muhrahmatullah on 09/10/18.
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


        val response: Deferred<AIResponse>? = coroutineScope.async {
            aiDataAIService.request(aiRequest)
        }

        coroutineScope.launch {
            sendMessageToServer(response?.await())
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