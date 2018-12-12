package com.star.quickezeservice.forgotpassword.modal;

import com.google.gson.annotations.SerializedName;

public class OtpModal {

    @SerializedName("mobile")
    String mobile;

    @SerializedName("otp")
    String otp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
