package com.gads2020.leaderboardapp.Services;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import retrofit2.Retrofit;

public class ServiceBuilder {

    private static final String URL = "https://gadsapi.herokuapp.com/";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory()

}
