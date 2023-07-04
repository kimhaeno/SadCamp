package com.example.sadcamp;

import java.io.Serializable;

public class ContactData implements Serializable {

    private String name;
    private String phoneNumber;
    private int profile;
    public ContactData(String name, String phoneNumber, int profile){
        this.profile = profile;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getPic()
    {
        return this.profile;
    }

    public void setPic(int profile) {
        this.profile = profile;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}