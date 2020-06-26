package com.social.socialdukan.Student.fragment.Internship.model;


public class EventRegistered {
    public String username;
    public String useremail;
    public String usernumber;
public String collegename;
    public String uid;
    public String eventid;
    public String leadername;
    public String teamid;
public String teamname;
public String status;
public String payment_status;
public String pass;
    public boolean selected;

    public EventRegistered(){

    }

    public EventRegistered(String status,String pass, String collegename, String payment_status, String leadername, String teamid, String username, String useremail, String usernumber, String uid, String teamname, String eventid) {
        this.username = username;
        this.useremail = useremail;
this.eventid=eventid;
this.leadername=leadername;
this.teamid=teamid;
this.status=status;
this.collegename=collegename;
this.teamname=teamname;
        this.usernumber = usernumber;
this.uid=uid;
this.payment_status=payment_status;
this.pass=pass;




    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
}