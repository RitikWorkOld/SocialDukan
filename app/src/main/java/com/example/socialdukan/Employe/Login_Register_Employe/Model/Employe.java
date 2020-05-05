package com.example.socialdukan.Employe.Login_Register_Employe.Model;


public class Employe {
    public String name;
    public String email;
    public String contactn;
    public String eid;
    public String website;
    public String password;
    public String profileimg;
    public String profilestatus;
    public String officialstatus;
    public String logostatus;
    public String descrip;
    public String cmpid;
    public String wcg1;
    public String from;
    public String to;
    public String no_opening;
    public String respo;
    public String incentBased;
    public String negotiatedTo;



    public Employe(){

    }

    public Employe(String negotiatedTo,String incentBased,String respo,String no_opening,String from,String to,String wcg1,String cmpid,String name, String email, String contactn, String eid, String password, String profileimg,String website, String profilestatus,String officialstatus,String descrip,String logostatus) {
        this.name = name;
        this.email = email;
this.officialstatus=officialstatus;
        this.contactn = contactn;
this.website=website;
this.negotiatedTo=negotiatedTo;
        this.eid = eid;
this.descrip=descrip;
this.no_opening=no_opening;
this.cmpid=cmpid;
this.incentBased=incentBased;
this.logostatus=logostatus;
this.respo=respo;
        this.password = password;
        this.from=from;
        this.to=to;
        this.profileimg = profileimg;
        this.profilestatus = profilestatus;
        this.wcg1=wcg1;

    }

    public String getNegotiatedTo() {
        return negotiatedTo;
    }

    public void setNegotiatedTo(String negotiatedTo) {
        this.negotiatedTo = negotiatedTo;
    }

    public String getIncentBased() {
        return incentBased;
    }

    public void setIncentBased(String incentBased) {
        this.incentBased = incentBased;
    }

    public String getRespo() {
        return respo;
    }

    public void setRespo(String respo) {
        this.respo = respo;
    }

    public String getNo_opening() {
        return no_opening;
    }

    public void setNo_opening(String no_opening) {
        this.no_opening = no_opening;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWcg1() {
        return wcg1;
    }

    public void setWcg1(String wcg1) {
        this.wcg1 = wcg1;
    }

    public String getCmpid() {
        return cmpid;
    }

    public void setCmpid(String cmpid) {
        this.cmpid = cmpid;
    }

    public String getLogostatus() {
        return logostatus;
    }

    public void setLogostatus(String logostatus) {
        this.logostatus = logostatus;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOfficialstatus() {
        return officialstatus;
    }

    public void setOfficialstatus(String officialstatus) {
        this.officialstatus = officialstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getContactn() {
        return contactn;
    }

    public void setContactn(String contactn) {
        this.contactn = contactn;
    }



    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getProfilestatus() {
        return profilestatus;
    }

    public void setProfilestatus(String profilestatus) {
        this.profilestatus = profilestatus;
    }
}