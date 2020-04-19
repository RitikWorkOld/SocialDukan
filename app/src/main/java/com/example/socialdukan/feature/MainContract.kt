package com.example.socialdukan.feature;
interface MainContract {

    interface View{


    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}