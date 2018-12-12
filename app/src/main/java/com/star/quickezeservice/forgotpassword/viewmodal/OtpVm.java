package com.star.quickezeservice.forgotpassword.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.star.quickezeservice.R;
import com.star.quickezeservice.api.APIConfiguration;
import com.star.quickezeservice.api.APIErrorHandler;
import com.star.quickezeservice.api.ApiCall;
import com.star.quickezeservice.api.GeneralResponse;
import com.star.quickezeservice.databinding.ActivityOtpBinding;
import com.star.quickezeservice.forgotpassword.UpdateActivity;
import com.star.quickezeservice.forgotpassword.modal.OtpModal;
import com.star.quickezeservice.utils.InternetChecker;
import com.star.quickezeservice.utils.MyProgressDialog;
import com.star.quickezeservice.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class OtpVm {
    Activity activity;
    ActivityOtpBinding binding;
    MyProgressDialog myProgressDialog;
    String otps;
    OtpModal otpModal;
    String mobile;
    public OtpVm(Activity activity, ActivityOtpBinding binding, String mobile) {
        this.activity=activity;
        this.binding=binding;
        this.mobile=mobile;
        otpModal=new OtpModal();
        myProgressDialog=new MyProgressDialog();

        textwatcher();
    }

    private void textwatcher() {

        binding.otpone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.otpone.getText().toString().trim().length() == 1) {

                    binding.otptwo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.otptwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.otptwo.getText().toString().trim().length() == 1) {

                    binding.otpthree.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        binding.otpthree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.otpthree.getText().toString().trim().length() == 1) {

                    binding.otpfour.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClicksubmit(View view){


        validation();

    }

    private void validation() {



        if(binding.otpone.getText().toString().length()==0||binding.otptwo.getText().toString().trim().length()==0||binding.otpthree.getText().toString().trim().length()==0||binding.otpfour.getText().toString().length()==0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the otp");

        } else {


            callApi();

        }

    }

    private void callApi() {


        otps=binding.otpone.getText().toString()+binding.otptwo.getText().toString()+binding.otpthree.getText().toString()+binding.otpfour.getText().toString();

        otpModal.setOtp(otps);
        otpModal.setMobile(mobile);


        if (InternetChecker.getInstance().isReachable()) {

            myProgressDialog.showDialog(activity);
            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.otp(otpModal);

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        if (responses.code() == 200) {
                            GeneralResponse generalResponse=responses.body();
                            if(generalResponse.isResult()){

                                Toast.makeText(activity,""+generalResponse.getMessage(),Toast.LENGTH_LONG).show();
                                activity.startActivity(new Intent(activity,UpdateActivity.class));
                            }else {

                                MySnackBar.getInstance().showNagativeSnackBar(activity,generalResponse.getMessage());
                            }



                        } else {
                            if (responses.body() != null) {

                                APIErrorHandler.getInstance().errorHandler(activity, responses.code(), responses.body().getMessage());

                            } else {

                                APIErrorHandler.getInstance().errorHandler(activity, responses.code(), responses.errorBody().string());

                            }
                        }
                    }, throwable -> {
                        myProgressDialog.dismissDialog();
                        MySnackBar.getInstance().showNagativeSnackBar(activity, activity.getString(R.string.something_went_wrong_while_retrieving_information));

                    });

        } else {
            MySnackBar.getInstance().showNagativeSnackBar(activity, activity.getString(R.string.no_internet));
        }




    }
}
