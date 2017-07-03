package com.example.tzapt.helpers;

import android.content.Context;
import android.content.res.AssetManager;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by itsix on 27/06/2017.
 */

public class Util {

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");
        properties.load(inputStream);
        return properties.getProperty(key);
    }

    public static String formatDate(CalendarDay date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();

        StringBuilder builder = new StringBuilder();

        builder.append(year + "-");

        if(month < 10) {
            builder.append("0" + month);
        } else {
            builder.append(month);
        }

        builder.append("-");

        if(day < 10) {
            builder.append("0" + day);
        } else {
            builder.append(day);
        }

        return builder.toString();
    }
}
