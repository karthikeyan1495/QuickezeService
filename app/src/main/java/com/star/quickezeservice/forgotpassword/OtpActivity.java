package com.star.quickezeservice.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityOtpBinding;
import com.star.quickezeservice.forgotpassword.viewmodal.OtpVm;

public class OtpActivity extends AppCompatActivity {


    ActivityOtpBinding binding;
    OtpVm otpVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();


    }

    private void bindview() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        Bundle bundle = getIntent().getExtras();

        String mobile = bundle.getString("mobile");
        otpVm = new OtpVm(this, binding,mobile);
        binding.setOtpVm(otpVm);

    }
}
