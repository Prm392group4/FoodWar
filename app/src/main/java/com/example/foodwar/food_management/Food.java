package com.example.foodwar.food_management;

public class Food {
    private String Name;
    private String Price;
    private int Image;

    public Food(String name, String price, int image) {
        Name = name;
        Price = price;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

}
