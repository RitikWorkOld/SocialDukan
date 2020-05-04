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


    public Employe(){

    }

    public Employe(String cmpid,String name, String email, String contactn, String eid, String password, String profileimg,String website, String profilestatus,String officialstatus,String descrip,String logostatus) {
        this.name = name;
        this.email = email;
this.officialstatus=officialstatus;
        this.contactn = contactn;
this.website=website;
        this.eid = eid;
this.descrip=descrip;
this.cmpid=cmpid;
this.logostatus=logostatus;
        this.password = password;
        this.profileimg = profileimg;
        this.profilestatus = profilestatus;

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