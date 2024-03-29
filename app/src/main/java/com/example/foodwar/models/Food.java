package com.example.foodwar.models;

public class Food {
    // avatar, full name, rate,restaurant, kind of food
    private String foodID;
    private String foodName;
    private Float foodRate;
    private String foodAvata;
    private String foodType;

    public Food() {
    }

    public Food(String foodID, String foodName, Float foodRate, String foodAvata, String foodType) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodRate = foodRate;
        this.foodAvata = foodAvata;
        this.foodType = foodType;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Float getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(Float foodRate) {
        this.foodRate = foodRate;
    }

    public String getFoodAvata() {
        return foodAvata;
    }

    public void setFoodAvata(String foodAvata) {
        this.foodAvata = foodAvata;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
