package com.example.socialdukan;

import com.google.firebase.database.DatabaseReference;

public class addexp_model {

    public String companyname;
    public String companystart;
    public String companyend;
    public String companyrole;
    public String companybenefits;
    public String expid;

    public addexp_model() {
    }

    public addexp_model(String companyname, String companystart, String companyend, String companyrole, String companybenefits, String expid) {
        this.companyname = companyname;
        this.companystart = companystart;
        this.companyend = companyend;
        this.companyrole = companyrole;
        this.companybenefits = companybenefits;
        this.expid = expid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanystart() {
        return companystart;
    }

    public void setCompanystart(String companystart) {
        this.companystart = companystart;
    }

    public String getCompanyend() {
        return companyend;
    }

    public void setCompanyend(String companyend) {
        this.companyend = companyend;
    }

    public String getCompanyrole() {
        return companyrole;
    }

    public void setCompanyrole(String companyrole) {
        this.companyrole = companyrole;
    }

    public String getCompanybenefits() {
        return companybenefits;
    }

    public void setCompanybenefits(String companybenefits) {
        this.companybenefits = companybenefits;
    }

    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }
}
