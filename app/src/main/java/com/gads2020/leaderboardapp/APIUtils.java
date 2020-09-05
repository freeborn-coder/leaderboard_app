package com.gads2020.leaderboardapp;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;
import com.gads2020.leaderboardapp.Models.UserData;
import com.gads2020.leaderboardapp.Services.GADSApiService;
import com.gads2020.leaderboardapp.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIUtils {

    public static GADSApiService downloadService;
    public static GADSApiService submitService;

    private APIUtils(){}

    public static void getTopLearners(DownloadCallback callback){
        if(downloadService == null){
            downloadService = ServiceBuilder.buildDownloadService(GADSApiService.class);
        }
        downloadService.getTopLearners().enqueue(new Callback<ArrayList<TopLearnerData>>() {
            @Override
            public void onResponse(Call<ArrayList<TopLearnerData>> call, Response<ArrayList<TopLearnerData>> response) {
                if(response.isSuccessful()) {
                    callback.onDownloadComplete(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TopLearnerData>> call, Throwable t) {
                callback.onDownloadFailed(t);
            }
        });

    }

    public static void getTopScorers(DownloadCallback callback){
        if(downloadService == null){
            downloadService = ServiceBuilder.buildDownloadService(GADSApiService.class);
        }

        downloadService.getTopScorers().enqueue(new Callback<ArrayList<TopScorerData>>() {
            @Override
            public void onResponse(Call<ArrayList<TopScorerData>> call, Response<ArrayList<TopScorerData>> response) {
                if(response.isSuccessful()) {
                    callback.onDownloadComplete(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TopScorerData>> call, Throwable t) {
                callback.onDownloadFailed(t);
            }
        });

    }

    public static void submitData(HashMap<String,String> params,SubmitCallback callback){
        if(submitService == null){
            submitService = ServiceBuilder.buildSubmitService(GADSApiService.class);
        }
        submitService.submit(params).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    callback.onSubmitSuccessful();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onSubmitFailed();
            }
        });
    }


}
