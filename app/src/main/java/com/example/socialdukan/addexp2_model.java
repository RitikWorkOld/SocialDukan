package com.example.socialdukan;

import com.google.firebase.database.DatabaseReference;

public class addexp2_model {

    public String achivmnts;

    public String expid;


    public addexp2_model() {
    }

    public addexp2_model(String achivmnts,String expid) {
        this.achivmnts = achivmnts;

        this.expid = expid;
    }

    public String getAchivmnts() {
        return achivmnts;
    }

    public void setAchivmnts(String achivmnts) {
        this.achivmnts = achivmnts;
    }



    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }
}
