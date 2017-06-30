package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.decorators.CustomDayViewDecorator;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Table;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tzapt on 6/25/2017.
 */

public class GetDaysOffTask extends DefaultTask {

    private MaterialCalendarView calendarView;

    public GetDaysOffTask(AppCompatActivity activity, MaterialCalendarView calendarView) {
        super(activity);
        this.calendarView = calendarView;
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

        getAllTables();

        return null;
    }

    private List<Table> getAllTables() {

        SyncHttpClient client = new SyncHttpClient();

        client.get(requestUrl + "/daysOff", new AsyncHttpResponseHandler() {


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

            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
