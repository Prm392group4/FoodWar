package com.example.foodwar.home_management;

public class Food {
    private String name;
    private String des;
    private String img;
    private String category;

    public Food() {
    }

    public Food(String name, String des, String img, String category) {
        this.name = name;
        this.des = des;
        this.img = img;
        this.category = category;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
