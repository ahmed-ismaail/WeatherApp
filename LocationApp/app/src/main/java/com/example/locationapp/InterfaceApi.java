package com.example.locationapp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {
    //"https://run.mocky.io/v3/b41be4b5-8d78-4870-8321-3089535f2a69/"

    @GET("b41be4b5-8d78-4870-8321-3089535f2a69/")
    Observable<List<ResponseLocation>> retrieveLocations();
 }
