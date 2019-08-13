package com.example.cityweather.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyWeather {
    @SerializedName("temp")
    @Expose
    private Temperature temperature;

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void setWeather(ArrayList<Weather>  weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public ArrayList<Weather>  getWeather() {
        return weather;
    }

}
