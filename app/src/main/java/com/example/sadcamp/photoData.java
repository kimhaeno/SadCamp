package com.example.sadcamp;

public class photoData {
    private int photo;
    private String info;

    public photoData(String info, int photo){
        this.photo = photo;
        this.info = info;
    }

    public int getPic()
    {
        return this.photo;
    }

    public void setPic(int photo) {
        this.photo = photo;
    }

    public String getInfo()
    {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
