package com.example.cityweather.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("min")
    @Expose
    float minTem;
    @SerializedName("max")
    @Expose
    float maxTem;

    public void setMinTem(float minTem) {
        this.minTem = minTem;
    }

    public void setMaxTem(float maxTem) {
        this.maxTem = maxTem;
    }

    public float getMinTem() {
        return minTem;
    }

    public float getMaxTem() {
        return maxTem;
    }
}
