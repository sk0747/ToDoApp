package com.shashi.todoapp;

public class Notes {
    public String id;
    public String title;
    public String discription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }


    public Notes() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Notes(String id,String title, String discription) {
        this.id=id;
        this.title = title;
        this.discription = discription;


    }

}
