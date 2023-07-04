package com.example.sadcamp;

import java.io.Serializable;

public class ContactData implements Serializable {

    private String name;
    private String email;
    private int profile;
    public ContactData(String name, String email, int profile){
        this.profile = profile;
        this.name = name;
        this.email = email;
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

    public String getMail()
    {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}