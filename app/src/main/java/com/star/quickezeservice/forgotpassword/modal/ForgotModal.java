package com.star.quickezeservice.forgotpassword.modal;

import com.google.gson.annotations.SerializedName;

public class ForgotModal {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @SerializedName("username")
    String username;

    @SerializedName("mobile")
    String mobile;

}
