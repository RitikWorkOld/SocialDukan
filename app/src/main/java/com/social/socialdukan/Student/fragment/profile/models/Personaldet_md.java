package com.social.socialdukan.Student.fragment.profile.models;

public class Personaldet_md {

    public String adress;
    public String dob;
    public String email;
    public String name;
    public String occupation;
    public String phnumber;
    public String uid;
    public String wanumber;

    public Personaldet_md() {
    }

    public Personaldet_md(String adress, String dob, String email, String name, String occupation, String phnumber, String uid, String wanumber) {
        this.adress = adress;
        this.dob = dob;
        this.email = email;
        this.name = name;
        this.occupation = occupation;
        this.phnumber = phnumber;
        this.uid = uid;
        this.wanumber = wanumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWanumber() {
        return wanumber;
    }

    public void setWanumber(String wanumber) {
        this.wanumber = wanumber;
    }
}
