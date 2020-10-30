package com.example.locationapp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://run.mocky.io/v3/";
    private InterfaceApi interfaceApi;

    private static class Singleton {
        private static final RetrofitClient RETROFIT_CLIENT = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return Singleton.RETROFIT_CLIENT;
    }

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        interfaceApi = retrofit.create(InterfaceApi.class);
    }

    public Observable<List<ResponseLocation>> retrieveLocations(){
        return  interfaceApi.retrieveLocations();
    }
}
