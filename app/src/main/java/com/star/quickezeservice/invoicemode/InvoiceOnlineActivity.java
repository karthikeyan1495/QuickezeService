package com.star.quickezeservice.invoicemode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityInvoiceOnlineBinding;
import com.star.quickezeservice.invoicemode.viewmodal.InvoiceOnlineVm;

public class InvoiceOnlineActivity extends AppCompatActivity {

    ActivityInvoiceOnlineBinding binding;
    InvoiceOnlineVm invoiceOnlineVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.activity_invoice_online);
        invoiceOnlineVm=new InvoiceOnlineVm(this,binding);
        binding.setInvoiceonlineVm(invoiceOnlineVm);

    }
}
