package com.gads2020.leaderboardapp.Models;

public class TopScorerData extends UserData {
    int score;

    public TopScorerData(String name, String country, String badgeUrl, int score) {
        super(name, country, badgeUrl);
        this.score = score;
    }

    public TopScorerData(){

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
