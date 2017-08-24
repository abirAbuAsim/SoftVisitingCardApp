package com.example.android.softvisitingcardapp.gallery;

import com.example.android.softvisitingcardapp.ModelClass.CardSent;

/**
 * Created by Belal on 14/04/17.
 */

public class CardViewed {

    private int id;
    private String name;
    private String email;
    private String designation;
    private String contact;
    private String website;
    private String address;
    private String organization;
    private String backgroundImage;
    private String logoImage;

    public CardViewed(int id, String name, String email, String designation, String contact,
                      String website, String address, String organization, String backgroundImage,
                      String logoImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.contact = contact;
        this.website = website;
        this.address = address;
        this.organization = organization;
        this.backgroundImage = backgroundImage;
        this.logoImage = logoImage;
    }

    public CardViewed(String name, String email, String designation, String contact, String website,
                      String address, String organization) {
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.contact = contact;
        this.website = website;
        this.address = address;
        this.organization = organization;
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

    public String getDesignation() {
        return designation;
    }

    public String getContact() {
        return contact;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getOrganization() {
        return organization;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getLogoImage() {
        return logoImage;
    }
}
