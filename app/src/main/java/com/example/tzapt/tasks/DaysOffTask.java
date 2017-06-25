package com.example.tzapt.tasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.models.Table;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tzapt on 6/25/2017.
 */

public class DaysOffTask extends AsyncTask<Object, Void, String> {

    private AppCompatActivity parentActivity;
    private String requestUrl;

    public DaysOffTask(AppCompatActivity activity) {
        this.parentActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        requestUrl = "http://192.168.1.104:8080";
    }

    @Override
    protected String doInBackground(Object... params) {

        getAllTables();

        return null;
    }

    private List<Table> getAllTables() {

        SyncHttpClient client = new SyncHttpClient();

        client.get(requestUrl + "/daysOff/getAll", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("asdad", new String(bytes));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("asdad", new String(bytes));
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
