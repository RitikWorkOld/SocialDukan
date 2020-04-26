package com.example.socialdukan.Student.feature;
interface MainContract {

    interface View{


    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}