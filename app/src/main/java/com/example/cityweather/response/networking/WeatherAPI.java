package com.example.cityweather.response.networking;

import com.example.cityweather.response.model.AllData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("forecast/daily?")
    Call<AllData> getAllData(@Query("lat") double latitude, @Query("lon") double longtitude, @Query("appid") String API_Key);

    @GET("forecast/daily?lat=21&lon=105&cnt=10&appid=e17e3700838ee8d924e0a3872b6bf1db")
    Call<AllData> getAllData();
}
