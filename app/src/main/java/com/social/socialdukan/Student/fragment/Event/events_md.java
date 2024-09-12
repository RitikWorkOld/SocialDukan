package com.social.socialdukan.Student.fragment.Event;


public class events_md {

    // Fields matching the Firebase structure
    private String amount;
    private String city;
    public String eventId;
    private String event_fb_link;
    private String event_insta_handle;
    private String eventdate;
    private String eventdesc;
    private String eventname;
    private String eventvenue;
    private String expfootfall;
    private String headcontact;
    private String heademail;
    private String headname;
    private String imageUrl;
    private String location;
    private String max_number;
    private String min_number;
    private String status;
    private String type_event;
    private String website_link;

    // Default constructor required for calls to DataSnapshot.getValue(events_md.class)
    public events_md() {
    }

    // Constructor to initialize all fields
    public events_md(String eventId,String amount, String city, String event_fb_link, String event_insta_handle, String eventdate,
                     String eventdesc, String eventname, String eventvenue, String expfootfall, String headcontact,
                     String heademail, String headname, String imageUrl, String location, String max_number,
                     String min_number, String status, String type_event, String website_link) {
        this.amount = amount;
        this.city = city;
        this.event_fb_link = event_fb_link;
        this.event_insta_handle = event_insta_handle;
        this.eventdate = eventdate;
        this.eventId = eventId;
        this.eventdesc = eventdesc;
        this.eventname = eventname;
        this.eventvenue = eventvenue;
        this.expfootfall = expfootfall;
        this.headcontact = headcontact;
        this.heademail = heademail;
        this.headname = headname;
        this.imageUrl = imageUrl;
        this.location = location;
        this.max_number = max_number;
        this.min_number = min_number;
        this.status = status;
        this.type_event = type_event;
        this.website_link = website_link;
    }

    // Getter and Setter methods for each field
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventvenue() {
        return eventvenue;
    }

    public void setEventvenue(String eventvenue) {
        this.eventvenue = eventvenue;
    }

    public String getExpfootfall() {
        return expfootfall;
    }

    public void setExpfootfall(String expfootfall) {
        this.expfootfall = expfootfall;
    }

    public String getHeadcontact() {
        return headcontact;
    }

    public void setHeadcontact(String headcontact) {
        this.headcontact = headcontact;
    }

    public String getHeademail() {
        return heademail;
    }

    public void setHeademail(String heademail) {
        this.heademail = heademail;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }
}
