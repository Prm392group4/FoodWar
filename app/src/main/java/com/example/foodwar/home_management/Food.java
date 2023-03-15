package com.example.foodwar.home_management;

public class Food {
    private String name;
    private String des;
    private String image;
    private String category;
    private String price;

    public Food() {
    }

    public Food(String name, String des, String image, String category, String price) {
        this.name = name;
        this.des = des;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
