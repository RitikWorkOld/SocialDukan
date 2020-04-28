package com.example.socialdukan.Student.fragment.other_services.model;

public class card_model {
    public String collab_name;
    public String key;
    public String categroy;
    public String location;
    public String date;
    public String intimguri;


    public card_model() {
    }
    public card_model(String collab_name,String key,String categroy,String location,String intimguri,String date) {
   this.collab_name=collab_name;
        this.intimguri = intimguri;
        this.key = key;
this.date=date;
        this.categroy = categroy;
        this.location = location;

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
