package com.example.cityweather.util;

import android.util.Log;

public class LogUtil {

    public static final String TAG = "CityWeather";

    public static void d(String message) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        String text = "[" + ste[1].getFileName() + ":" + ste[1].getLineNumber() + ":" + ste[1].getMethodName() + "()] ";
        Log.d(TAG, text + message);
    }

    public static void e(String message) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        String text = "[" + ste[1].getFileName() + ":" + ste[1].getLineNumber() + ":" + ste[1].getMethodName() + "()] ";
        Log.e(TAG, text + message);
    }
}
