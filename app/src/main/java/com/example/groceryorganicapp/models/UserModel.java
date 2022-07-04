package com.example.groceryorganicapp.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class UserModel {
    String name;
    String email;
    Bitmap img;

    public UserModel() {
    }

    public UserModel(String name, String email, Bitmap img) {
        this.name = name;
        this.email = email;
        this.img = img;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
