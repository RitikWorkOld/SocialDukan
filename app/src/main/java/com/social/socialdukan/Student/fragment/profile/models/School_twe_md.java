package com.social.socialdukan.Student.fragment.profile.models;

public class School_twe_md {

    public String schoolboard;
    public String schoolendy;
    public String schoolname;
    public String schoolper;
    public String schoolstarty;
    public String schoolstream;

    public String uid;


    public School_twe_md() {
    }

    public School_twe_md(String schoolboard, String schoolendy, String schoolname, String schoolper, String schoolstarty, String uid,String schoolstream) {
        this.schoolboard = schoolboard;
        this.schoolendy = schoolendy;
        this.schoolname = schoolname;
        this.schoolper = schoolper;
        this.schoolstarty = schoolstarty;
        this.schoolstream = schoolstream;
        this.uid = uid;

    }

    public String School_twe_md() {
        return schoolboard;
    }

    public String getSchoolboard() {
        return schoolboard;
    }

    public void setSchoolboard(String schoolboard) {
        this.schoolboard = schoolboard;
    }

    public String getSchoolendy() {
        return schoolendy;
    }

    public void setSchoolendy(String schoolendy) {
        this.schoolendy = schoolendy;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchoolper() {
        return schoolper;
    }

    public void setSchoolper(String schoolper) {
        this.schoolper = schoolper;
    }

    public String getSchoolstarty() {
        return schoolstarty;
    }

    public void setSchoolstarty(String schoolstarty) {
        this.schoolstarty = schoolstarty;
    }

    public String getSchoolstream() {
        return schoolstream;
    }

    public void setSchoolstream(String schoolstream) {
        this.schoolstream = schoolstream;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
