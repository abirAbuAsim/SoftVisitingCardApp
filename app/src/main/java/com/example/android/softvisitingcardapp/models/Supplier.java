package com.example.android.softvisitingcardapp.models;

/**
 * Created by Belal on 14/04/17.
 */

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String companyName;

    public Supplier(int id, String name, String address, String phoneNumber, String companyName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    public Supplier(String name, String address, String phoneNumber, String companyName) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }
}
