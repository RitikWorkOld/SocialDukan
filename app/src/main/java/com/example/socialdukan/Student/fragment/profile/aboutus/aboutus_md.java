package com.example.socialdukan.Student.fragment.profile.aboutus;


public class aboutus_md {
    public String icon;
    public String number;
    public String title;
    public String id;


    public aboutus_md() {
    }


    public aboutus_md(String icon,String number,String title,String id) {
       this.icon=icon;
       this.number=number;
       this.title=title;
       this.id=id;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
