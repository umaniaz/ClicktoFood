package com.food.clicktofood.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.myapps.x.Helper.NetworkInterceptor;

/**
 * Created by Khan on 2/21/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        /* This line is where we add the interceptor to the retrofit service  */
        //httpClient.addInterceptor(new NetworkInterceptor(context));
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(160, TimeUnit.SECONDS);
        httpClient.readTimeout(160, TimeUnit.SECONDS);

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

//    public static Retrofit getClientContext(String url, Context context) {
//        Gson gson = new GsonBuilder().setLenient().create();
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        /* This line is where we add the interceptor to the retrofit service  */
//        httpClient.addInterceptor(new NetworkInterceptor(context));
//        httpClient.addInterceptor(logging);
//        httpClient.connectTimeout(30, TimeUnit.SECONDS);
//        httpClient.readTimeout(30, TimeUnit.SECONDS);
//
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(url)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .client(httpClient.build())
//                    .build();
//        }
//        return retrofit;
//    }
}