package com.example.cityweather.base;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

public class BaseViewModel extends ViewModel {
    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
