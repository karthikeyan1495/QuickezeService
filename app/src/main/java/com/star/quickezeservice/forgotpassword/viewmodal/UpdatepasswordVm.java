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
import com.star.quickezeservice.databinding.ActivityUpdatepasswordBinding;
import com.star.quickezeservice.forgotpassword.modal.UpdateModal;
import com.star.quickezeservice.login.MainActivity;
import com.star.quickezeservice.shardprefeence.MySession;
import com.star.quickezeservice.utils.InternetChecker;
import com.star.quickezeservice.utils.MyProgressDialog;
import com.star.quickezeservice.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UpdatepasswordVm {
    Activity activity;
    ActivityUpdatepasswordBinding binding;
    MyProgressDialog myProgressDialog;
    UpdateModal updateModal;

    public UpdatepasswordVm(Activity activity, ActivityUpdatepasswordBinding binding) {
        this.activity = activity;
        this.binding = binding;
        updateModal=new UpdateModal();
        myProgressDialog=new MyProgressDialog();
    }

    public void onClicksubmit(View view) {

        if (binding.password.getText().toString().trim().length() == 0) {
            MySnackBar.getInstance().showNagativeSnackBar(activity, "Please enter the new password");
        } else if (!binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            MySnackBar.getInstance().showNagativeSnackBar(activity, "Please enter the correct password");

        } else {

            callApi();

        }

    }

    private void callApi() {

        updateModal.setUsername(MySession.getInstance(activity).getUserName());
        updateModal.setPassword(binding.password.getText().toString());

        if (InternetChecker.getInstance().isReachable()) {

            myProgressDialog.showDialog(activity);
            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.updatepassword(updateModal);

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        if (responses.code() == 200) {

                            GeneralResponse generalResponse=responses.body();
                            if(generalResponse.isResult()){

                                Toast.makeText(activity,generalResponse.getMessage(),Toast.LENGTH_LONG).show();
                                activity.startActivity(new Intent(activity,MainActivity.class));

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
