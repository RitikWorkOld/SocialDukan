package com.example.socialdukan.Student.fragment.profile.models;

public class Diploma_md {



    public String dipclgname;
    public String dippercentage;

    public String dipcorsname;

    public String uid;


    public Diploma_md() {
    }

    public Diploma_md(String dipclgname,String dippercentage,String dipcorsname,String uid) {
        this.dipcorsname = dipcorsname;

        this.dipclgname = dipclgname;
        this.dippercentage = dippercentage;

        this.uid = uid;

    }

    public String getDipclgname() {
        return dipclgname;
    }

    public void setDipclgname(String dipclgname) {
        this.dipclgname = dipclgname;
    }

    public String getDippercentage() {
        return dippercentage;
    }

    public void setDippercentage(String dippercentage) {
        this.dippercentage = dippercentage;
    }

    public String getDipcorsname() {
        return dipcorsname;
    }

    public void setDipcorsname(String dipcorsname) {
        this.dipcorsname = dipcorsname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
