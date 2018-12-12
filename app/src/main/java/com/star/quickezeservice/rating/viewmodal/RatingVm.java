package com.star.quickezeservice.rating.viewmodal;

import android.app.Activity;

import com.star.quickezeservice.databinding.ActivityRatingBinding;

public class RatingVm {
    Activity activity;
    ActivityRatingBinding binding;

    public RatingVm(Activity  activity, ActivityRatingBinding binding) {
        this.activity=activity;
        this.binding=binding;
    }
}
