package com.gads2020.leaderboardapp.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String DOWNLOAD_DATA_URL = "https://gadsapi.herokuapp.com/api/";
    private static final String POST_DATA_URL = "https://docs.google.com/forms/d/e/";



    private static Retrofit retrofitForDownload = new Retrofit.Builder()
            .baseUrl(DOWNLOAD_DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitForSubmit = new Retrofit.Builder()
            .baseUrl(POST_DATA_URL)
            .build();


    public static <S> S buildDownloadService(Class<S> serviceType){
        return retrofitForDownload.create(serviceType);
    }

    public static <S> S buildSubmitService(Class<S> serviceType){
        return retrofitForSubmit.create(serviceType);
    }

}
