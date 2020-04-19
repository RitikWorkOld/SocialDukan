package com.example.socialdukan;

import com.google.firebase.database.DatabaseReference;

public class addexp1_model {

    public String skills;

    public String expid;


    public addexp1_model() {
    }

    public addexp1_model(String skills,String expid) {
        this.skills = skills;

        this.expid = expid;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }



    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }
}
