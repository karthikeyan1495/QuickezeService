package com.star.quickezeservice.servicerequest.viewmodal;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.star.quickezeservice.databinding.ActivityServicerequestBinding;
import com.star.quickezeservice.servicerequest.adaptor.ServiceRequestAdaptor;

public class ServiceRequestVm {
    Activity activity;
    ActivityServicerequestBinding binding;
    String list[]={"Mr.Raj has request for service","Mr.Raj has request for service","Mr.Raj has request for service"};
    public ServiceRequestVm(Activity activity, ActivityServicerequestBinding binding) {

        this.activity=activity;
        this.binding=binding;
        setuprecycleView();
    }

    private void setuprecycleView() {

        binding.serviceRequestRecycleview.setLayoutManager(new LinearLayoutManager(activity));
        ServiceRequestAdaptor adaptor=new ServiceRequestAdaptor(activity,list);
        binding.serviceRequestRecycleview.setAdapter(adaptor);

    }
}
