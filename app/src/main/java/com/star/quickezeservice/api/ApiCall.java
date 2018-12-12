package com.star.quickezeservice.api;



import com.star.quickezeservice.forgotpassword.modal.ForgotModal;
import com.star.quickezeservice.forgotpassword.modal.OtpModal;
import com.star.quickezeservice.forgotpassword.modal.UpdateModal;
import com.star.quickezeservice.login.modal.Login;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCall {

    @POST("service_engineer_login")
    Observable<Response<GeneralResponse>> login(@Body Login login);

    @POST("service_engineer_forgotpassword")
    Observable<Response<GeneralResponse>> forgotpassword(@Body ForgotModal forgotModal);

    @POST("service_engineer_verify_otp")
    Observable<Response<GeneralResponse>> otp(@Body OtpModal otpModal);

    @POST("service_engineer_update_password")
    Observable<Response<GeneralResponse>> updatepassword(@Body UpdateModal updateModal);







}
