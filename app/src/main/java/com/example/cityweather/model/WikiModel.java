package com.example.cityweather.model;

import android.databinding.BaseObservable;

public class WikiModel extends BaseObservable {
    public int progress;

    public WikiModel(int progress) {
        this.progress = progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }
}
