package com.example.socialdukan;


public class User {
    public String name;
    public String email;
    public String contactn;
    public String uid;
    public String password;
    public String profileimg;
    public String profilestatus;


    public User(){

    }

    public User(String name, String email,String contactn, String uid, String password,String profileimg,String profilestatus) {
        this.name = name;
        this.email = email;

        this.contactn = contactn;

        this.uid = uid;

        this.password = password;
        this.profileimg = profileimg;
        this.profilestatus = profilestatus;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getContactn() {
        return contactn;
    }

    public void setContactn(String contactn) {
        this.contactn = contactn;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getProfilestatus() {
        return profilestatus;
    }

    public void setProfilestatus(String profilestatus) {
        this.profilestatus = profilestatus;
    }
}