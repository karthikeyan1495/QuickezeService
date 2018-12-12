package com.star.quickezeservice.invoicemode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityInvoiceCashBinding;
import com.star.quickezeservice.invoicemode.viewmodal.InvoiceCashVm;

public class InvoiceCashActivity extends AppCompatActivity {

    ActivityInvoiceCashBinding binding;
    InvoiceCashVm invoiceCashVm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding=DataBindingUtil.setContentView(this,R.layout.activity_invoice_cash);
        invoiceCashVm=new InvoiceCashVm(this,binding);
        binding.setInoicecashVm(invoiceCashVm);


    }
}
