package com.example.android.softvisitingcardapp.models;

/**
 * Created by Belal on 14/04/17.
 */

public class Brand {
    private int id;
    private String name;
    private String details;


    public Brand(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Brand(int id, String name, String details){
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}
