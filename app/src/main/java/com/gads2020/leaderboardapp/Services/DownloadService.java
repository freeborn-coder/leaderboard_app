package com.gads2020.leaderboardapp.Services;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DownloadService {

    @GET("hours")
    Call<List<TopLearnerData>> getLearningLeaders();

    @GET("skilliq")
    Call<List<TopScorerData>> getTopScorers();
}
