package com.example.cityweather.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.example.cityweather.Constant;
import com.example.cityweather.view.WikiActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtil {
    private static final String TAG = "AppUtil";

    public static String getDayOfWeek(long timestamp) {
        String day = null;
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        day = sdf.format(date);
        return day;
    }

    public static float convertKelvinToCelsius(float kelvin) {
        return Math.round(kelvin - 273.15f);
    }

    public static void gotoWikiScreen(Context mContext, String cityName) {
        Intent intent = new Intent(mContext, WikiActivity.class);
        intent.putExtra(Constant.PLACE_WIKI_LINK, (Constant.WIKI_LINK + cityName.replaceAll(" ", "_")).trim());
        mContext.startActivity(intent);
    }

    public static void openMap(Context mContext, double lat, double lng) {
        String destination = lat + "," + lng;
        Uri gmmIntentUri = Uri.parse("geo:" + destination);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.setPackage("com.google.android.apps.maps");
        mContext.startActivity(mapIntent);
    }
}
