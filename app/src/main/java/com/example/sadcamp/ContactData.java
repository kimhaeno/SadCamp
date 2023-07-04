package com.example.sadcamp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class ContactData implements Serializable {

    private String name;
    private String phoneNumber;
    private byte[] profile;

    private String bio; // Introduce

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public ContactData(String name, String phoneNumber, Bitmap profile, String bio){
        this.profile = bitmapToByteArray(profile);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public Bitmap getPic()
    {
        return byteArrayToBitmap(this.profile);
    }

    public void setPic(Bitmap profile) {
        this.profile = bitmapToByteArray(profile);
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


    public String getBio()
    {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }



}