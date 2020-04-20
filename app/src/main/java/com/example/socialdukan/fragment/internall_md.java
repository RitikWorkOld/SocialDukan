package com.example.socialdukan.fragment;

public class internall_md {

    public String amount;
    public String cmpname;
    public String duration;
    public String intimguri;
    public String key;
    public String location;
    public String intname;
    public String worktime;

    public internall_md() {
    }

    public internall_md(String amount, String cmpname, String duration, String intimguri, String key, String location, String intname,String worktime) {
        this.amount = amount;
        this.cmpname = cmpname;
        this.duration = duration;
        this.intimguri = intimguri;
        this.key = key;
        this.location = location;
        this.intname = intname;
        this.worktime = worktime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCmpname() {
        return cmpname;
    }

    public void setCmpname(String cmpname) {
        this.cmpname = cmpname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntimguri() {
        return intimguri;
    }

    public void setIntimguri(String intimguri) {
        this.intimguri = intimguri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntname() {
        return intname;
    }

    public void setIntname(String intname) {
        this.intname = intname;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }
}