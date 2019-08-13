package com.example.cityweather.view;

import com.example.cityweather.R;
import com.example.cityweather.adapter.DailyWeatherAdapter;
import com.example.cityweather.base.BaseActivity;
import com.example.cityweather.databinding.ActivityWeatherBinding;
import com.example.cityweather.model.DailyWeatherModel;
import com.example.cityweather.viewmodel.WeekWeatherViewModel;

import java.util.ArrayList;

public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeekWeatherViewModel> {
    public DailyWeatherAdapter dailyWeatherAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    public Class<WeekWeatherViewModel> getClassViewModel() {
        return WeekWeatherViewModel.class;
    }

    @Override
    public void initView() {
        binding.setWeather(this);
        dailyWeatherAdapter = new DailyWeatherAdapter(this, new ArrayList<>());
        viewModel.getWeatherData(21, 105).observe(this, list->{
            dailyWeatherAdapter.setListData(list);
        });
    }
}
