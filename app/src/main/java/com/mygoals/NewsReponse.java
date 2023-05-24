package com.mygoals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsReponse {
    @SerializedName("meals")
    private List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setMeals(List<News> news) {
        this.news = news;
    }
}
