package com.example.groceryorganicapp.models;

public class ProductModel {
    String imgUrl;
    String prodName;

    public ProductModel() {
    }

    public ProductModel(String imgUrl, String prodName) {
        this.imgUrl = imgUrl;
        this.prodName = prodName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
