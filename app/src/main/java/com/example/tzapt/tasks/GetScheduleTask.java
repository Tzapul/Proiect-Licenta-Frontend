package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tzapt.helpers.Util;
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
 * Created by itsix on 28/06/2017.
 */

public class GetScheduleTask extends DefaultTask {

    private TableLayout daysTable;

    public GetScheduleTask(AppCompatActivity parentActivity, TableLayout daysTable) {
        super(parentActivity);
        this.daysTable = daysTable;
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

        getSchedule();

        return null;
    }

    private List<Table> getSchedule() {

        SyncHttpClient client = new SyncHttpClient();

        client.get(requestUrl + "/schedule", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                response = new String(bytes);
                code = i;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if(bytes != null) {
                    code = i;
                    response = new String(bytes);
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
                JSONArray array = new JSONArray(response);
                TableRow row;
                TextView dayText;
                TextView workingHoursText;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object =  array.getJSONObject(i);

                    String date = object.getString("date");
                    String start = object.getString("start");
                    String end = object.getString("end");

                    row = new TableRow(parentActivity);
                    dayText = new TextView(parentActivity);
                    workingHoursText = new TextView(parentActivity);

                    dayText.setText(date);
                    workingHoursText.setText(start + " - " + end);

                    row.addView(dayText);
                    row.addView(workingHoursText);

                    daysTable.addView(row);
                }
            } catch (JSONException e) {
                Toast.makeText(parentActivity, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
