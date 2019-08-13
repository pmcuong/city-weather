package com.example.cityweather.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllData {
    @SerializedName("list")
    @Expose
    private ArrayList<DailyWeather> listData;

    @SerializedName("cnt")
    @Expose
    private String cnt;

    public ArrayList<DailyWeather> getListData() {
        return listData;
    }

    public String getCnt() {
        return cnt;
    }

    public void setListData(ArrayList<DailyWeather> listData) {
        this.listData = listData;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}
