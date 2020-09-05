package com.gads2020.leaderboardapp;

import com.gads2020.leaderboardapp.Models.UserData;

import java.util.ArrayList;

public interface DownloadCallback {
    void onDownloadComplete(ArrayList<UserData> response);
    void onDownloadFailed(Throwable t);
}
