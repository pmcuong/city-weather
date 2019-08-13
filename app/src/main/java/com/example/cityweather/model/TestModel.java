package com.example.cityweather.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

public class TestModel extends AndroidViewModel {
    private static final String TAG = "TestModel";
    String text;
    private MutableLiveData<String> liveText = new MutableLiveData<>();

    public TestModel(@NonNull Application application) {
        super(application);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged: "+s.toString());
        setLiveText(s.toString());
    }

    public void setLiveText(String text){
        liveText.setValue(text);
    }

    public MutableLiveData<String> getLiveText(){
        return liveText;
    }
}
