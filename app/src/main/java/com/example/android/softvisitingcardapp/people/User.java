package com.example.android.softvisitingcardapp.people;

/**
 * Created by Belal on 14/04/17.
 */

public class User {

    private int id;
    private String name;
    private String email;



    public User(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


}
