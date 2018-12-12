package com.star.quickezeservice.servicerequest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityServicerequestBinding;
import com.star.quickezeservice.servicerequest.viewmodal.ServiceRequestVm;

public class ServiceRequestActivity extends AppCompatActivity {

    ActivityServicerequestBinding binding;
    ServiceRequestVm serviceRequestVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_servicerequest);
        serviceRequestVm=new ServiceRequestVm(this,binding);
        binding.setServicerequestVm(serviceRequestVm);


    }
}
