package com.star.quickezeservice.api;


import com.star.quickezeservice.MyApp;
import com.star.quickezeservice.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfiguration {



    private static final APIConfiguration ourInstance = new APIConfiguration();

    public static APIConfiguration getInstance() {
        return ourInstance;
    }

    private APIConfiguration() {
    }

    public <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        httpClient.readTimeout(600, TimeUnit.SECONDS);
        httpClient.connectTimeout(600, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.header("Accept", "application/json");

               /* requestBuilder.header("device_token", FirebaseInstanceId.getInstance().getToken());*/
             //   requestBuilder.header("device_type", "0");// 0 Means Android
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = builder.baseUrl(MyApp.getContext().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
