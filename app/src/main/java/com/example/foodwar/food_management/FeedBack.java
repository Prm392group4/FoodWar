package com.example.foodwar.food_management;

public class FeedBack {
    private String feedBack;
    private float ratingStar;

    public FeedBack() {
    }

    public FeedBack(String feedBack, float ratingStar) {
        this.feedBack = feedBack;
        this.ratingStar = ratingStar;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public float getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(float ratingStar) {
        this.ratingStar = ratingStar;
    }

}
