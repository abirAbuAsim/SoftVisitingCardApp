package com.example.android.softvisitingcardapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal on 14/04/17.
 */

public class Result {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;

    @SerializedName("brand")
    private Brand brand;

    public Result(Boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public Result(Boolean error, String message, Brand brand) {
        this.error = error;
        this.message = message;
        this.brand = brand;
    }

    public Result(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
