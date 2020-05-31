package com.social.socialdukan.Student.Miscellaneous;


public class Pers_detail {
    public String adress;
    public String dob;
    public String occupation;
    public String wanumber;



    public Pers_detail(){

    }

    public Pers_detail(String adress, String dob,String occupation, String wanumber) {
        this.adress = adress;
        this.dob = dob;

        this.occupation = occupation;

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWanumber() {
        return wanumber;
    }

    public void setWanumber(String wanumber) {
        this.wanumber = wanumber;
    }
}