package com.example.joseramos.lyricsappjlrf.data.api.factory;

import android.util.Log;

import com.example.joseramos.lyricsappjlrf.data.api.interceptors.ApiInterceptor;
import com.example.joseramos.lyricsappjlrf.presentation.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    private static final String TAG = ServiceFactory.class.getSimpleName();
    private static final int MAX_READ_TIME_OUT_SECONDS = 180;
    private static final int MAX_CONNECTION_TIME_OUT_SECONDS = 60;

    public static <T> T createRetrofitService(final Class<T> clazz, String baseUrl, ApiInterceptor sdcInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (sdcInterceptor != null) {
            httpClientBuilder.addInterceptor(sdcInterceptor);
        }

        //FIXME: We have to implement something in order to know if it is debug mode or prod mode
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "OkHttp " + message);
            }
        });

        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Logger interceptor added
        OkHttpClient client = httpClientBuilder
                .addInterceptor(loggerInterceptor)
                .readTimeout(MAX_READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(MAX_CONNECTION_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }
}