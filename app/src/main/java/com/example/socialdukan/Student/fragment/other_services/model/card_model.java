package com.example.socialdukan.Student.fragment.other_services.model;

public class card_model {
    public String collab_name;      //brand name
    public String key;
    public String categroy;     //genre
    public String location;
    public String date;
    public String intimguri;
    public String paymenttype;
    public String instahandle;
    public String link;
    public String primary;


    public card_model() {
    }


    public card_model(String collab_name, String key, String categroy, String location, String intimguri, String date, String paymenttype, String instahandle, String link,String primary) {
        this.collab_name=collab_name;
        this.intimguri = intimguri;
        this.key = key;
        this.date=date;
        this.primary=primary;
        this.categroy = categroy;
        this.location = location;
        this.paymenttype=paymenttype;
        this.instahandle=instahandle;
        this.link=link;

    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getInstahandle() {
        return instahandle;
    }

    public void setInstahandle(String instahandle) {
        this.instahandle = instahandle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCollab_name() {
        return collab_name;
    }

    public void setCollab_name(String collab_name) {
        this.collab_name = collab_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getIntimguri() {
        return intimguri;
    }

    public void setIntimguri(String intimguri) {
        this.intimguri = intimguri;
    }




}
