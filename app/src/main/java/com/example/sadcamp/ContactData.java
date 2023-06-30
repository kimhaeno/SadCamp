package com.example.sadcamp;

public class ContactData {
    private int profile;
    private String name;
    private String phoneNumber;

    public ContactData(int profile, String name, String phoneNumber){
        this.profile = profile;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getPic()
    {
        return this.profile;
    }

    public String getName()
    {
        return this.name;
    }

    public String getNumber()
    {
        return this.phoneNumber;
    }
}