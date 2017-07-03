package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.models.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import static android.R.attr.parentActivityName;
import static android.R.attr.phoneNumber;

/**
 * Created by tzapt on 7/1/2017.
 */

public class SaveReservationTask extends DefaultTask {

    public SaveReservationTask(AppCompatActivity parentActivity) {
        super(parentActivity);
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

        try {
            makeReservation(params[0], params[1]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void makeReservation(Object reservat, Object id) throws UnsupportedEncodingException, JSONException {
        Reservation reservation = (Reservation) reservat;

        SyncHttpClient client = new SyncHttpClient();

        JSONObject params = new JSONObject();

        params.put("name", reservation.getName());
        params.put("email", reservation.getEmail());
        params.put("date", reservation.getDate());
        params.put("hour", reservation.getHour());
        params.put("phone", reservation.getPhone());
        params.put("people", reservation.getPeople());


        StringEntity entity = new StringEntity(params.toString());


        client.post(parentActivity, requestUrl + "/reservations/" + id.toString(), entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                response = new String(bytes);
                code = i;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if(bytes != null) {
                    response = new String(bytes);
                    code = i;
                }
            }
        });
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (code == 200) {
            try {
                JSONObject reservation = new JSONObject(response);
                FirebaseMessaging.getInstance().subscribeToTopic(reservation.getString("date"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(parentActivity, "The booking has been made!", Toast.LENGTH_SHORT).show();
            parentActivity.finish();
        } else {
            try {
                JSONObject object = new JSONObject(response);
                Toast.makeText(parentActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
