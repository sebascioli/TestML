package com.seba.testml.srv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RestSrv {

    private RestAPI restAPI;

    public RestSrv(String baseUrl, long timeout_s) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(timeout_s, SECONDS);
        httpClient.readTimeout(timeout_s, SECONDS);
        httpClient.writeTimeout(timeout_s, SECONDS);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();
        restAPI = retrofit.create(RestAPI.class);

    }

    public RestAPI getRestAPI() {
        return restAPI;
    }

    public void setRestAPI(RestAPI restAPI) {
        this.restAPI = restAPI;
    }

}