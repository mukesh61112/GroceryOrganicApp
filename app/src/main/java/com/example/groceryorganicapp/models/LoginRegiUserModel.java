package com.example.groceryorganicapp.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class LoginRegiUserModel {
    String username;
    String email;
    String password;
    Uri img;

    public      Uri getImg() {
        return img;
    }

    public void setImg(     Uri img) {
        this.img = img;
    }

    public LoginRegiUserModel(String username, String email, String password,      Uri img) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.img=img;
    }

    public LoginRegiUserModel() {
    }

    public LoginRegiUserModel(Uri img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
