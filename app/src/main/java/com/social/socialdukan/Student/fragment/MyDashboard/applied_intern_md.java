package com.social.socialdukan.Student.fragment.MyDashboard;

public class applied_intern_md {

    public String answer1;
    public String answer2;
    public String answer3;
    public String companyid;
    public String key;
    public String userid;
    public String status;
    public String username;
    public String usernumber;
    public String userimg;
    public String internid;

    public applied_intern_md() {
    }

    public applied_intern_md(String internid,String username,String userimg,String usernumber,String answer1, String answer2, String answer3, String companyid, String key, String userid,String status) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.companyid = companyid;
        this.key = key;
        this.userid = userid;
        this.username=username;
        this.internid=internid;
        this.userimg=userimg;
        this.usernumber=usernumber;
        this.status = status;
    }

    public String getInternid() {
        return internid;
    }

    public void setInternid(String internid) {
        this.internid = internid;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
