package com.star.quickezeservice.servicerequeststatus;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityServicestatusBinding;
import com.star.quickezeservice.location.LocationActivity;
import com.star.quickezeservice.servicerequeststatus.viewmodal.ServiceRequeststatusVm;

public class ServiceRequestStatusActivity extends AppCompatActivity {

    ActivityServicestatusBinding binding;
    ServiceRequeststatusVm serviceRequeststatusVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_servicestatus);
        serviceRequeststatusVm=new ServiceRequeststatusVm(this,binding);
        binding.setServicerequestStatusVm(serviceRequeststatusVm);

        binding.servicerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(ServiceRequestStatusActivity.this,LocationActivity.class));

            }
        });

    }
}
