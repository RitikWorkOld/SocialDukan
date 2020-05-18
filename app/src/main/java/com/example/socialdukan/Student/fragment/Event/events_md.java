package com.example.socialdukan.Student.fragment.Event;



public class events_md {

    public String eventname;

    public String intimguri;
public String range;
    public String desc1;
    public String desc2;
    public String number_of_member;
    public String event_date;
    public String event_fb_link;
    public String event_insta_handle;
    public String event_website_link;
    public String eventid;
    public String max_number;
    public String min_number;
    public String type_of_event;
    public String location;
    public String amount;
    public String indi_or_team;


    public String event_desc;


    public events_md() {
    }

    public events_md(String amount,String indi_or_team,String range,String location,String type_of_event,String min_number,String max_number,String eventid,String event_website_link,String number_of_member,String event_date,String event_fb_link,String event_insta_handle,String eventname, String event_desc,  String intimguri,String desc1,String desc2) {
        this.eventname = eventname;
        this.intimguri = intimguri;
this.range=range;
this.amount=amount;
this.indi_or_team=indi_or_team;
        this.event_desc = event_desc;
        this.desc1=desc1;
        this.desc2=desc2;
        this.location=location;
        this.type_of_event=type_of_event;
        this.min_number=min_number;
        this.max_number=max_number;
        this.eventid=eventid;
        this.event_website_link=event_website_link;
        this.number_of_member=number_of_member;
        this.event_date=event_date;
        this.event_fb_link=event_fb_link;
        this.event_insta_handle=event_insta_handle;


    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIndi_or_team() {
        return indi_or_team;
    }

    public void setIndi_or_team(String indi_or_team) {
        this.indi_or_team = indi_or_team;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getNumber_of_member() {
        return number_of_member;
    }

    public void setNumber_of_member(String number_of_member) {
        this.number_of_member = number_of_member;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_fb_link() {
        return event_fb_link;
    }

    public void setEvent_fb_link(String event_fb_link) {
        this.event_fb_link = event_fb_link;
    }

    public String getEvent_insta_handle() {
        return event_insta_handle;
    }

    public void setEvent_insta_handle(String event_insta_handle) {
        this.event_insta_handle = event_insta_handle;
    }

    public String getEvent_website_link() {
        return event_website_link;
    }

    public void setEvent_website_link(String event_website_link) {
        this.event_website_link = event_website_link;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getMax_number() {
        return max_number;
    }

    public void setMax_number(String max_number) {
        this.max_number = max_number;
    }

    public String getMin_number() {
        return min_number;
    }

    public void setMin_number(String min_number) {
        this.min_number = min_number;
    }

    public String getType_of_event() {
        return type_of_event;
    }

    public void setType_of_event(String type_of_event) {
        this.type_of_event = type_of_event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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



    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }
}