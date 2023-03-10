package com.example.foodwar.entity;

public class UserProfile {
    private String name;
    private String mobile_num;
    private String email;
    private String adress;

    public UserProfile() {
    }

    public UserProfile(String name, String mobile_num, String email, String adress) {
        this.name = name;
        this.mobile_num = mobile_num;
        this.email = email;
        this.adress = adress;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getmobile_num() {
        return mobile_num;
    }

    public void setmobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getadress() {
        return adress;
    }

    public void setadress(String adress) {
        this.adress = adress;
    }
}
