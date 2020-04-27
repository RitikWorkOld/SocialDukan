package com.example.socialdukan.Student.Chat_bot.feature;
interface MainContract {

    interface View{


    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}