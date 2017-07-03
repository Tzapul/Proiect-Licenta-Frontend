package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.adapters.ScheduleDayAdapter;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.DayOff;
import com.example.tzapt.models.ScheduleDay;
import com.example.tzapt.models.Table;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tzapt on 7/3/2017.
 */

public class GetScheduleDaysTask extends DefaultTask {

    private ScheduleDayAdapter adapter;

    public GetScheduleDaysTask(AppCompatActivity parentActivity, ScheduleDayAdapter adapter) {
        super(parentActivity);
        this.adapter = adapter;
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

        getDaysOff();

        return null;
    }

    private List<Table> getDaysOff() {

        SyncHttpClient client = new SyncHttpClient();

        client.get(requestUrl + "/scheduleDays", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                response = new String(bytes);
                code = i;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if(bytes != null) {
                    Log.i("asdad", new String(bytes));
                }
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(code == 200) {
            try {
                adapter.clearData();
                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++) {
                    JSONObject scheduleDayObject = array.getJSONObject(i);

                    int id = scheduleDayObject.getInt("id");
                    String day = scheduleDayObject.getString("day");
                    int start = scheduleDayObject.getInt("start");
                    int end = scheduleDayObject.getInt("end");

                    ScheduleDay scheduleDay = new ScheduleDay(id, day, start, end);

                    adapter.add(scheduleDay);
                }

                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
