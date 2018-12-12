package com.star.quickezeservice.login.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.star.quickezeservice.R;
import com.star.quickezeservice.api.APIConfiguration;
import com.star.quickezeservice.api.APIErrorHandler;
import com.star.quickezeservice.api.ApiCall;
import com.star.quickezeservice.api.GeneralResponse;
import com.star.quickezeservice.databinding.ActivityMainBinding;
import com.star.quickezeservice.forgotpassword.ForgotActivity;
import com.star.quickezeservice.forgotpassword.OtpActivity;
import com.star.quickezeservice.forgotpassword.UpdateActivity;
import com.star.quickezeservice.login.modal.Login;
import com.star.quickezeservice.servicerequest.ServiceRequestActivity;
import com.star.quickezeservice.utils.InternetChecker;
import com.star.quickezeservice.utils.MyProgressDialog;
import com.star.quickezeservice.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginVm {

    Activity activity;
    ActivityMainBinding binding;
    Login login;
    MyProgressDialog myProgressDialog;

    public LoginVm(Activity activity, ActivityMainBinding binding, Login login) {

        this.activity=activity;
        this.binding=binding;
        this.login=login;
        myProgressDialog=new MyProgressDialog();

    }

    private void validation() {


        if(binding.username.getText().toString().trim().length()==0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the username");

        }else if (binding.password.getText().toString().trim().length()==0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the password");

        }else {

            callApi();
        }

    }

    private void callApi() {

        if (InternetChecker.getInstance().isReachable()) {

            myProgressDialog.showDialog(activity);
            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.login(login);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        if (responses.code() == 200) {

                            activity.startActivity(new Intent(activity,ServiceRequestActivity.class));

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

    public void onClicklogin(View view){

        validation();
    }

    public void onClickforgotpassword(View view){

        activity.startActivity(new Intent(activity,ForgotActivity.class));

       // activity.startActivity(new Intent(activity,UpdateActivity.class));

    }
}
