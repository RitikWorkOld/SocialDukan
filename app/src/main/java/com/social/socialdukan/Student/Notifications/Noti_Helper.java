package com.social.socialdukan.Student.Notifications;

public class Noti_Helper {

    String Title;
    String Desc;
    String Date;
    String notiid;


    public Noti_Helper() {
    }

    public Noti_Helper(String title, String desc, String notiid,  String date) {
        Title = title;
        Desc = desc;
        Date = date;
        this.notiid = notiid;


    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getNotiid() {
        return notiid;
    }

    public void setNotiid(String notiid) {
        this.notiid = notiid;
    }

}
