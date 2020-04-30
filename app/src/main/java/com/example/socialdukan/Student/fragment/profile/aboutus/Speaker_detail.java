package com.example.socialdukan.Student.fragment.profile.aboutus;

public class Speaker_detail {

    public String name;
    public String desc;
    public String id;
    public String imguri;
   public String insta;
   public String mail;

    public Speaker_detail() {
    }

    public Speaker_detail(String name, String desc, String id, String imguri,String insta,String mail) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.imguri = imguri;
        this.insta=insta;
this.mail=mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }


}
