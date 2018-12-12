package com.star.quickezeservice.forgotpassword.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.star.quickezeservice.R;
import com.star.quickezeservice.api.APIConfiguration;
import com.star.quickezeservice.api.APIErrorHandler;
import com.star.quickezeservice.api.ApiCall;
import com.star.quickezeservice.api.GeneralResponse;
import com.star.quickezeservice.databinding.ActivityForgotBinding;
import com.star.quickezeservice.forgotpassword.OtpActivity;
import com.star.quickezeservice.forgotpassword.UpdateActivity;
import com.star.quickezeservice.forgotpassword.modal.ForgotModal;
import com.star.quickezeservice.servicerequest.ServiceRequestActivity;
import com.star.quickezeservice.shardprefeence.MySession;
import com.star.quickezeservice.utils.InternetChecker;
import com.star.quickezeservice.utils.MyProgressDialog;
import com.star.quickezeservice.utils.MySnackBar;
import com.star.quickezeservice.utils.Util;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ForgotVm {
    Activity activity;
    ActivityForgotBinding binding;
    ForgotModal modal;
    MyProgressDialog myProgressDialog;
    public ForgotVm(Activity activity, ActivityForgotBinding binding, ForgotModal modal) {

        this.activity=activity;
        this.binding=binding;
        this.modal=modal;
        myProgressDialog=new MyProgressDialog();


    }

    private void validation() {


        if(binding.username.getText().toString().trim().length()==0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the username");

        }else if(! Util.getInstance().Mobile_No_Validator(modal.getMobile())){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter correct mobile Number");
        }else {

            callApi();
        }


    }

    private void callApi() {


        if (InternetChecker.getInstance().isReachable()) {

            myProgressDialog.showDialog(activity);
            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.forgotpassword(modal);

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        if (responses.code() == 200) {

                            GeneralResponse generalResponse=responses.body();

                            if(generalResponse.isResult()){
                                MySession.getInstance(activity).saveUserName(modal.getUsername());
                                Toast.makeText(activity,generalResponse.getMessage(),Toast.LENGTH_LONG).show();
                                activity.startActivity(new Intent(activity,OtpActivity.class).putExtra("mobile",modal.getMobile()));

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

    public void onClickotp(View view){

        validation();
    }
}
