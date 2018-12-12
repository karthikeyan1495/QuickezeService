package com.star.quickezeservice.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityForgotBinding;
import com.star.quickezeservice.forgotpassword.modal.ForgotModal;
import com.star.quickezeservice.forgotpassword.viewmodal.ForgotVm;

public class ForgotActivity extends AppCompatActivity {

    ActivityForgotBinding binding;
    ForgotVm forgotVm;
    ForgotModal modal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot);
        modal=new ForgotModal();
        forgotVm=new ForgotVm(this,binding,modal);
        binding.setForgotVm(forgotVm);
        binding.setForgot(modal);

    }
}
