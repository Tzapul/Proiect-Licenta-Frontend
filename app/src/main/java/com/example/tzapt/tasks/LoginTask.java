package com.example.tzapt.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tzapt.activities.UserMainView;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import static android.R.id.message;

/**
 * Created by tzapt on 6/19/2017.
 */

public class LoginTask extends DefaultTask {

    public LoginTask(AppCompatActivity parentActivity) {
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
            sendPost(params[0], params[1]);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void sendPost(Object username, Object password) throws IOException, JSONException, SocketTimeoutException {
        SyncHttpClient client = new SyncHttpClient();

        JSONObject params = new JSONObject();

        params.put("username", username.toString());
        params.put("password", password.toString());

        StringEntity entity = new StringEntity(params.toString());

        client.addHeader("Content-Type", "application/json");
        client.post(parentActivity, requestUrl + "/login", entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                code = i;
                response = new String(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if(bytes != null) {
                    code = i;
                    response = new String(bytes);
                }
            }
        });
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(message);
        if(code == 200) {
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
            try {
                if(response != null) {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(parentActivity, object.getString("message"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(parentActivity, "An error has occurred while doing the request!", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
