package com.example.groceryorganicapp.models;

public class SeachRVModel {
    String image;
    String name;
    String price;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SeachRVModel() {
    }

    public SeachRVModel(String image, String name, String price,String type) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.type=type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
