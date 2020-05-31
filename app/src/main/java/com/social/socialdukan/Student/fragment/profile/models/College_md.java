package com.social.socialdukan.Student.fragment.profile.models;

public class College_md {

    public String collegename;
    public String collegecourse;
    public String collegedept;
    public String collegeend;
    public String collegeper;
    public String collegestart;

    public String uid;


    public College_md() {
    }

    public College_md(String collegename, String collegecourse, String collegedept, String collegeend, String collegeper,String collegestart, String uid) {
        this.collegename = collegename;
        this.collegecourse = collegecourse;
        this.collegedept = collegedept;
        this.collegeend = collegeend;
        this.collegeper = collegeper;
        this.collegestart = collegestart;
        this.uid = uid;

    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getCollegecourse() {
        return collegecourse;
    }

    public void setCollegecourse(String collegecourse) {
        this.collegecourse = collegecourse;
    }

    public String getCollegedept() {
        return collegedept;
    }

    public void setCollegedept(String collegedept) {
        this.collegedept = collegedept;
    }

    public String getCollegeend() {
        return collegeend;
    }

    public void setCollegeend(String collegeend) {
        this.collegeend = collegeend;
    }

    public String getCollegeper() {
        return collegeper;
    }

    public void setCollegeper(String collegeper) {
        this.collegeper = collegeper;
    }

    public String getCollegestart() {
        return collegestart;
    }

    public void setCollegestart(String collegestart) {
        this.collegestart = collegestart;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
