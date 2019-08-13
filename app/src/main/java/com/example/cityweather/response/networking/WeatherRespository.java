package com.example.cityweather.response.networking;

import android.arch.lifecycle.MutableLiveData;

import com.example.cityweather.model.DailyWeatherModel;
import com.example.cityweather.response.model.AllData;
import com.example.cityweather.response.model.DailyWeather;
import com.example.cityweather.util.AppUtil;
import com.example.cityweather.util.LogUtil;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRespository {
    private static WeatherRespository weatherRespository;
    private WeatherAPI weatherAPI;

    public static WeatherRespository getInstance() {
        if (weatherRespository == null) {
            weatherRespository = new WeatherRespository();
        }
        return weatherRespository;
    }

    private WeatherRespository() {
        weatherAPI = WeatherService.cteateService(WeatherAPI.class);
    }

    public MutableLiveData<ArrayList<DailyWeatherModel>> getWeatherOfWeek(double latitude, double longtitude, String API_Key) {
        final MutableLiveData<ArrayList<DailyWeatherModel>> weekWeatherMutableLiveData = new MutableLiveData<>();
        weatherAPI.getAllData().enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                if (response.isSuccessful()) {
                    AllData allData = response.body();
                    long currentMilis = System.currentTimeMillis();
                    long milisOnDay = 24 * 60 * 60 * 1000;
                    if (allData.getListData() != null) {
                        ArrayList<DailyWeather> weekWeather = allData.getListData();
                        ArrayList<DailyWeatherModel> listWeather = new ArrayList<>();

                        for (int i = 0; i < weekWeather.size(); i++) {
                            String dayOfWeek = AppUtil.getDayOfWeek(currentMilis);
                            int minTemp = (int) AppUtil.convertKelvinToCelsius(weekWeather.get(i).getTemperature().getMinTem());
                            int maxTemp = (int) AppUtil.convertKelvinToCelsius(weekWeather.get(i).getTemperature().getMaxTem());
                            String status = weekWeather.get(i).getWeather().get(0).getStatus();
                            DailyWeatherModel dailyWeatherModel = new DailyWeatherModel(dayOfWeek, status, minTemp, maxTemp);
                            listWeather.add(dailyWeatherModel);
                            currentMilis += milisOnDay;
                        }
                        weekWeatherMutableLiveData.postValue(listWeather);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {
                LogUtil.d("getWeatherOfWeek: onFailure: " + t.getMessage());
            }
        });
        return weekWeatherMutableLiveData;
    }
}
