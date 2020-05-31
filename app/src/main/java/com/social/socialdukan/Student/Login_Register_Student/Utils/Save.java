package com.social.socialdukan.Student.Login_Register_Student.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Save {
    //void saving
    public static  void  save(Context ctx, String name, String value){

        SharedPreferences s = ctx.getSharedPreferences("clipcodes", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt=s.edit();
        edt.putString(name,value);
        edt.apply();
    }

    //get value return string
    public static String read(Context ctx, String name, String defaultvalue){
        SharedPreferences s=ctx.getSharedPreferences("clipcodes", Context.MODE_PRIVATE);
        return s.getString(name,defaultvalue);

    }
}
