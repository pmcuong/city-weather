package com.example.cityweather.adapter;

import android.content.Context;
import com.example.cityweather.BR;
import com.example.cityweather.R;
import com.example.cityweather.base.BaseAdapter;
import com.example.cityweather.databinding.LayoutItemDailyWeatherBinding;
import com.example.cityweather.model.DailyWeatherModel;
import java.util.ArrayList;

public class DailyWeatherAdapter extends BaseAdapter<DailyWeatherModel, LayoutItemDailyWeatherBinding> {
    public DailyWeatherAdapter(Context mContext, ArrayList<DailyWeatherModel> listData) {
        super(mContext, listData);
    }

    public DailyWeatherAdapter(Context mContext){
        super(mContext, new ArrayList<DailyWeatherModel>());
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_item_daily_weather;
    }

    @Override
    public int getId() {
        return BR.dailyWeather;
    }


}
