package com.star.quickezeservice.servicerequest.adaptor;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ServiceRequestAdaptorBinding;
import com.star.quickezeservice.servicerequeststatus.ServiceRequestStatusActivity;

public class ServiceRequestAdaptor extends RecyclerView.Adapter<ServiceRequestAdaptor.ViewHolder> {

    Activity activity;
    String list[];

    public ServiceRequestAdaptor(Activity activity, String[] list) {
        this.activity=activity;
        this.list=list;
    }

    @NonNull
    @Override
    public ServiceRequestAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ServiceRequestAdaptorBinding binding = DataBindingUtil.inflate(inflater, R.layout.service_request_adaptor, parent, false);
        return new ServiceRequestAdaptor.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceRequestAdaptor.ViewHolder holder, int position) {

        holder.binding.text.setText(list[position]);


        holder.binding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.startActivity(new Intent(activity,ServiceRequestStatusActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ServiceRequestAdaptorBinding binding;

        public ViewHolder(@NonNull ServiceRequestAdaptorBinding binding) {
            super(binding.getRoot());

            this.binding=binding;
        }
    }
}
