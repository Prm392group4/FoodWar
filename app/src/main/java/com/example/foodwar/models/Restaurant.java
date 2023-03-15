package com.example.foodwar.models;

public class Restaurant {
    private String id;
    private String name;
    private Integer rate;
    private String image;
    private String des;
    private String address;

    public Restaurant() {
    }

    public Restaurant(String resID, String resName, Integer resRate, String resAvata, String resType, String resAddress) {
        this.id = resID;
        this.name = resName;
        this.rate = resRate;
        this.image = resAvata;
        this.des = resType;
        this.address = resAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
