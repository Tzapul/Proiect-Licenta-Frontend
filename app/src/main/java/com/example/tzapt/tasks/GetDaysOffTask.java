package com.example.tzapt.tasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.decorators.CustomDayViewDecorator;
import com.example.tzapt.models.DayOff;
import com.example.tzapt.models.Table;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tzapt on 6/25/2017.
 */

public class GetDaysOffTask extends AsyncTask<Object, Void, String> {

    private AppCompatActivity parentActivity;
    private String requestUrl;
    private String response;
    private int code;
    private MaterialCalendarView calendarView;

    public GetDaysOffTask(AppCompatActivity activity, MaterialCalendarView calendarView) {
        this.parentActivity = activity;
        this.calendarView = calendarView;
    }

    @Override
    protected void onPreExecute() {
        requestUrl = "http://192.168.1.105:8080";
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
                response = new String(bytes);
                code = i;
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

        if(code == 200) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                HashSet<CalendarDay> calendarDays = new HashSet<>();

                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++) {
                    JSONObject dayOff = array.getJSONObject(i);

                    String date = dayOff.getString("date");

                    calendarDays.add(new CalendarDay((sdf).parse(date)));
                }

                DayViewDecorator decorator = new CustomDayViewDecorator(calendarDays);

                calendarView.addDecorator(decorator);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
