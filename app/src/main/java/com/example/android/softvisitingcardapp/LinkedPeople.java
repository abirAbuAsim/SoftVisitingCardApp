package com.example.android.softvisitingcardapp;

/**
 * Created by absak on 7/25/2017.
 */

public class LinkedPeople {
    private String mLinkName;
    private String mLinkEmail;



    public LinkedPeople(String linkName, String linkEmail) {
        mLinkName = linkName;
        mLinkEmail = linkEmail;
    }

    public String getmLinkName() {
        return mLinkName;
    }

    public String getmLinkEmail() {
        return mLinkEmail;
    }

    @Override
    public String toString() {
        return "LinkedPeople{" +
                "mLinkName='" + mLinkName + '\'' +
                ", mLinkEmail='" + mLinkEmail + '\'' +
                '}';
    }
}
