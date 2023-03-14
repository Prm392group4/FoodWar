package com.example.foodwar.entity;

public class UserProfile {
    private String name;
    private String mobile_num;
    private String email;
    private String adress;
    private String image;

    public UserProfile() {
    }

    public UserProfile(String name, String mobile_num, String email, String adress, String image) {
        this.name = name;
        this.mobile_num = mobile_num;
        this.email = email;
        this.adress = adress;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
