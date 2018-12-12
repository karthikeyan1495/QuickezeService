package com.star.quickezeservice.location.viewmodal;

import android.app.Activity;

import com.star.quickezeservice.databinding.ActivityLocationBinding;

public class LocationVm {

    Activity activity;
    ActivityLocationBinding binding;
    public LocationVm(Activity activity, ActivityLocationBinding binding) {

        this.activity=activity;
        this.binding=binding;
    }
}
