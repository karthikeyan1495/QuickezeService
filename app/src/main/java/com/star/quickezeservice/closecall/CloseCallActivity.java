package com.star.quickezeservice.closecall;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.closecall.viewmodal.CloseCallVm;
import com.star.quickezeservice.databinding.ActivityClosecallBinding;

public class CloseCallActivity extends AppCompatActivity {


    ActivityClosecallBinding binding;
    CloseCallVm closeCallVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();
    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_closecall);
        closeCallVm=new CloseCallVm(this,binding);
        binding.setCloseCallVm(closeCallVm);


    }
}
