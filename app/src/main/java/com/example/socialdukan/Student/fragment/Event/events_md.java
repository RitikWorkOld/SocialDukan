package com.example.socialdukan.Student.fragment.Event;



public class events_md {

    public String eventname;

    public String intimguri;
    public String key;
    public String desc1;
    public String desc2;

    public String event_desc;


    public events_md() {
    }

    public events_md(String eventname, String event_desc,  String intimguri, String key,String desc1,String desc2) {
        this.eventname = eventname;
        this.intimguri = intimguri;
        this.key = key;
        this.event_desc = event_desc;
        this.desc1=desc1;
        this.desc2=desc2;


    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
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

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }
}