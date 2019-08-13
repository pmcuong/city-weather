package com.example.cityweather.model;

public class DailyWeatherModel {
    private String dayOfWeek;
    private String weatherStatus;
    private int minTemperature;
    private int maxTemperature;

    public DailyWeatherModel(String dayOfWeek, String weatherStatus, int minTemperature, int maxTemperature) {
        this.dayOfWeek = dayOfWeek;
        this.weatherStatus = weatherStatus;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public String getMinTemperature() {
        return String.valueOf(minTemperature);
    }

    public String getMaxTemperature() {
        return String.valueOf(maxTemperature);
    }

}



