package com.gads2020.leaderboardapp.Services;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GADSApiService {

    @GET("hours")
    Call<ArrayList<TopLearnerData>> getTopLearners();

    @GET("skilliq")
    Call<ArrayList<TopScorerData>> getTopScorers();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submit(@FieldMap HashMap<String, String> fields);
}
