package com.example.tzapt.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.activities.UserMainView;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by tzapt on 6/22/2017.
 */

public class RegisterTask extends DefaultTask {


    public RegisterTask(AppCompatActivity parentActivity) {
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
            registerClient(params[0], params[1], params[2], params[3], params[4], params[5]);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void registerClient(Object firstname, Object lastname, Object email, Object phone, Object username, Object password) throws IOException, JSONException {

        SyncHttpClient client = new SyncHttpClient();

        JSONObject params = new JSONObject();

        params.put("firstName", firstname.toString());
        params.put("lastName", lastname.toString());
        params.put("email", email.toString());
        params.put("phoneNumber", phone.toString());
        params.put("username", username.toString());
        params.put("password", password.toString());
        params.put("notificationKey", FirebaseInstanceId.getInstance().getToken());

        StringEntity entity = new StringEntity(params.toString());

        client.addHeader("Content-Type", "application/json");
        client.post(parentActivity, requestUrl + "/register", entity, "application/json", new AsyncHttpResponseHandler() {

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
                String username = object.getString("username");
                String firstname = object.getString("firstName");
                String lastname = object.getString("lastName");
                String phoneNumber = object.getString("phoneNumber");
                String email = object.getString("email");
                String password = object.getString("password");

                Account account = new Account(username, password, email);
                PersonDetails personDetails = new PersonDetails(firstname,lastname,phoneNumber);

                client = new User(id, account,personDetails);
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
