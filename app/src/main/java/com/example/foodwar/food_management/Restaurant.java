package com.example.foodwar.food_management;

public class Restaurant {
    private String name;
    private String phone;
    private String image;
    private String address;

    public Restaurant() {
    }

    public Restaurant(String name, String phone, String image, String address) {
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
