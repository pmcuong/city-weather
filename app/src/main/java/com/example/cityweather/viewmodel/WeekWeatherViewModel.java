package com.example.cityweather.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.example.cityweather.Constant;
import com.example.cityweather.base.BaseViewModel;
import com.example.cityweather.model.DailyWeatherModel;
import com.example.cityweather.response.networking.WeatherRespository;

import java.util.ArrayList;

public class WeekWeatherViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<DailyWeatherModel>> weatherData;
    private WeatherRespository weatherRespository;

    public WeekWeatherViewModel() {
        if (weatherData == null) {
            weatherData = new MutableLiveData<>();
        }
    }

    public void setWeatherData(MutableLiveData<ArrayList<DailyWeatherModel>> weatherData) {
        this.weatherData = weatherData;
    }

    public MutableLiveData<ArrayList<DailyWeatherModel>> getWeatherData(double latitude, double longtitude) {
        updateData(latitude, longtitude);
        return weatherData;
    }

    public void updateData(double latitude, double longtitude){
        weatherRespository = WeatherRespository.getInstance();
        weatherData = weatherRespository.getWeatherOfWeek(latitude, longtitude, Constant.API_KEY);
    }

}
