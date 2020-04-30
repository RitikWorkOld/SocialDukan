package com.example.socialdukan.Student.fragment.profile.aboutus;


public class aboutus_md {
    public String icon;
    public String number;
    public String title;
    public String id;

    public String description;
    public String photo;
    public String name;
    public String position;
    public String work;
    public String imageone;


    public aboutus_md() {
    }


    public aboutus_md(String icon,String number,String title,String id,String description,String photo,String name,String position,String work,String imageone) {
       this.icon=icon;
       this.number=number;
       this.title=title;
       this.id=id;
       //2
        this.description=description;
        this.photo=photo;
        this.name=name;
        this.position=position;
        this.work=work;
        this.imageone=imageone;

    }

    public String getImageone() {
        return imageone;
    }

    public void setImageone(String imageone) {
        this.imageone = imageone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
