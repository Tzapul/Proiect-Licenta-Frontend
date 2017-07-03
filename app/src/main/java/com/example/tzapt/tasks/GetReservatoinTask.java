package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;

import com.example.tzapt.adapters.MyReservationsAdapter;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.Reservation;
import com.google.firebase.messaging.FirebaseMessaging;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tzapt on 6/26/2017.
 */

public class GetReservatoinTask extends DefaultTask {

    private MyReservationsAdapter reservationsAdapter;
    private Client client;

    public GetReservatoinTask(AppCompatActivity parentActivity, MyReservationsAdapter reservationsAdapter, Client client) {
        super(parentActivity);
        this.reservationsAdapter = reservationsAdapter;
        this.client = client;
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

        getReservations();

        return null;
    }

    private void getReservations() {

        SyncHttpClient client = new SyncHttpClient();

        client.get(requestUrl + "/reservations/" + this.client.getId(), new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                code = i;
                response = new String(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                code = i;
                System.out.println(i);
                System.out.println(new String(bytes));
            }
        });
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (code == 200) {
            try {
                reservationsAdapter.clearData();
                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String date = object.getString("date");
                    String phone = object.getString("phone");
                    int people = object.getInt("people");
                    int hour = object.getInt("hour");
                    List<String> tablesName = new ArrayList<>();

                    JSONArray tables = object.getJSONArray("tablesList");
                    for(int j = 0; j < tables.length(); j++) {
                        String table = tables.getString(i);
                        tablesName.add(table);
                    }

                    Reservation reservation = new Reservation(id, name, email, date, phone, people, hour, tablesName);
                    reservationsAdapter.add(reservation);
                }

                reservationsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
        }
    }

}
