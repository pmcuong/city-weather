package com.example.cityweather.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.example.cityweather.base.BaseViewModel;
import com.example.cityweather.model.WikiModel;

public class WikiViewModel extends BaseViewModel {
    MutableLiveData<WikiModel> data;
    MutableLiveData<Integer> progress;

    public WikiViewModel () {
        if (data==null){
            data = new MutableLiveData<>();
        }
        if (progress==null){
            progress = new MutableLiveData<>();
        }
    }

    public MutableLiveData<WikiModel> getData() {
        return data;
    }

    public void setData(WikiModel wikiModel) {
        this.data.setValue(wikiModel);
    }

    public void setProgress(int progress) {
        this.progress.setValue(progress);
    }

    public MutableLiveData getProgress() {
        return progress;
    }
}
