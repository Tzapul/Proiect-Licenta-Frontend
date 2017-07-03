package com.example.tzapt.tasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.tzapt.helpers.Util;

import java.io.IOException;

/**
 * Created by itsix on 27/06/2017.
 */

class DefaultTask extends AsyncTask<Object, Void, String> {

    String requestUrl;
    int code;
    String response;
    AppCompatActivity parentActivity;

    public DefaultTask(AppCompatActivity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    protected void onPreExecute() {
        try {
            requestUrl = Util.getProperty("localhost", parentActivity.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Object... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
