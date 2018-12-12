package com.star.quickezeservice.location;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityLocationBinding;
import com.star.quickezeservice.location.viewmodal.LocationVm;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    LocationVm locationVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();
    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_location);
        locationVm=new LocationVm(this,binding);
        binding.setLocationVm(locationVm);

    }
}
