package com.example.joseramos.lyricsappjlrf.data.api.interceptors;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class ApiInterceptor implements Interceptor {

    private static final String API_KEY = "apikey";

    @Inject
    public ApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter(API_KEY, "cefc42c0c5113012b86905d4860dfd25").build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

}