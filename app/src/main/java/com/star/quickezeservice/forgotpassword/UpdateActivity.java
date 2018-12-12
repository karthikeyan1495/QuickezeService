package com.star.quickezeservice.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityUpdatepasswordBinding;
import com.star.quickezeservice.forgotpassword.viewmodal.UpdatepasswordVm;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdatepasswordBinding binding;
    UpdatepasswordVm updatepasswordVm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindview();


    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_updatepassword);

        updatepasswordVm=new UpdatepasswordVm(this,binding);
        binding.setUpdatepasswordVm(updatepasswordVm);


    }
}
