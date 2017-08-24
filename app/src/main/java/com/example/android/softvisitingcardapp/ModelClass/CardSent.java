
package com.example.android.softvisitingcardapp.ModelClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CardSent implements Parcelable {

    @SerializedName("sender_name")
    private String sender;
    @SerializedName("sender_email")
    private String email;
    @SerializedName("sender_designation")
    private String designation;
    @SerializedName("sender_contact")
    private String contact;
    @SerializedName("sender_website")
    private String website;
    @SerializedName("sender_address")
    private String address;
    @SerializedName("sender_organization")
    private String organization;
    @SerializedName("sender_logo_image")
    private String logoImage;
    @SerializedName("sender_background_image")
    private String backgroundImage;

    public CardSent() {
    }



    public CardSent(String sender, String email, String designation,
                    String contact, String website, String address,
                    String organization, String backgroundImage, String logoImage) {
        this.sender = sender;
        this.email = email;
        this.designation = designation;
        this.contact = contact;
        this.website = website;
        this.address = address;
        this.organization = organization;
        this.logoImage = logoImage;
        this.backgroundImage = backgroundImage;
    }

    public final static Parcelable.Creator<CardSent> CREATOR = new Creator<CardSent>() {

        @SuppressWarnings({
            "unchecked"
        })
        public CardSent createFromParcel(Parcel in) {
            CardSent instance = new CardSent();
            instance.sender = ((String) in.readValue((String.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.designation = ((String) in.readValue((String.class.getClassLoader())));
            instance.contact = ((String) in.readValue((String.class.getClassLoader())));
            instance.website = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.organization = ((String) in.readValue((String.class.getClassLoader())));
            instance.backgroundImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.logoImage = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CardSent[] newArray(int size) {
            return (new CardSent[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sender);
        dest.writeValue(email);
        dest.writeValue(designation);
        dest.writeValue(contact);
        dest.writeValue(website);
        dest.writeValue(address);
        dest.writeValue(organization);
        dest.writeValue(backgroundImage);
        dest.writeValue(logoImage);
    }

    public int describeContents() {
        return  0;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
