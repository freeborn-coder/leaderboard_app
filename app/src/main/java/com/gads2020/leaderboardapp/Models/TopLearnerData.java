package com.gads2020.leaderboardapp.Models;

public class TopLearnerData extends UserData{

    int hours;

    public TopLearnerData(String name, String country, String badgeUrl, int hours) {
        super(name, country, badgeUrl);
        this.hours = hours;
    }

    public TopLearnerData(){

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
