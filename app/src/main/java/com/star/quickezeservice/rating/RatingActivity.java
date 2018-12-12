package com.star.quickezeservice.rating;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityRatingBinding;
import com.star.quickezeservice.rating.viewmodal.RatingVm;

public class RatingActivity extends AppCompatActivity {

    ActivityRatingBinding binding;
    RatingVm ratingVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();
    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_rating);
        ratingVm=new RatingVm(this,binding);
        binding.setRatingVm(ratingVm);

    }
}
