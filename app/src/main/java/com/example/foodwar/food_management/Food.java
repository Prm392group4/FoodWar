package com.example.foodwar.food_management;

public class Food {
    private String name;
    private String description;
    private String image;
    private String category;
    private String price;
    private String restaurant;
    public Food() {
    }

    public Food(String name, String description, String image, String category, String price, String restaurant) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
