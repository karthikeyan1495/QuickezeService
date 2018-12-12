package com.star.quickezeservice.invoicemode.viewmodal;

import android.app.Activity;

import com.star.quickezeservice.databinding.ActivityInvoiceCashBinding;

public class InvoiceCashVm {
    Activity activity;
    ActivityInvoiceCashBinding binding;
    public InvoiceCashVm(Activity activity, ActivityInvoiceCashBinding binding) {
        this.activity=activity;
        this.binding=binding;
    }
}
