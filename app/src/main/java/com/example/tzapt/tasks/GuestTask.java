package com.example.tzapt.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.activities.UserMainView;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.Guest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by tzapt on 6/26/2017.
 */

public class GuestTask extends DefaultTask {


    public GuestTask(AppCompatActivity parentActivity) {
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
            sendPost();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void sendPost() throws UnsupportedEncodingException {
        SyncHttpClient client = new SyncHttpClient();

        JSONObject params = new JSONObject();

        StringEntity entity = new StringEntity(params.toString());
        client.addHeader("Content-Type", "application/json");

        client.post(parentActivity, requestUrl + "/guest", entity, "application/json", new AsyncHttpResponseHandler() {

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
            Intent intent = new Intent(parentActivity, UserMainView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            Client client = null;
            try {
                JSONObject object = new JSONObject(response);

                int id = object.getInt("id");

                client = new Guest(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Bundle b = new Bundle();
            b.putSerializable("client", client);
            intent.putExtras(b);
            parentActivity.startActivity(intent);
            parentActivity.finish();
        } else {
            Log.i("asdsa", String.valueOf(code));
        }
    }
}
