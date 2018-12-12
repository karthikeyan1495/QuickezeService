package com.star.quickezeservice.forgotpassword.modal;

import com.google.gson.annotations.SerializedName;

public class UpdateModal {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

}
