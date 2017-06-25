package com.example.tzapt.tasks;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tzapt.activities.MainView;
import com.example.tzapt.activities.RegisterActivity;
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

public class RegisterTask extends AsyncTask<Object, Void, String> {

    private String requestUrl;
    private int code;
    private String response;
    private AppCompatActivity parentActivity;


    public RegisterTask(RegisterActivity registerActivity) {
        this.parentActivity = registerActivity;
    }

    @Override
    protected void onPreExecute() {
        requestUrl = "http://192.168.1.104:8080";
    }

    @Override
    protected String doInBackground(Object... params) {

        try {
            registerClient(params[0], params[1], params[2], params[3], params[4], params[5]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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

        StringEntity entity = new StringEntity(params.toString());

        System.out.println(params);

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
            Intent intent = new Intent(parentActivity, MainView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Client", response);
            parentActivity.startActivity(intent);
            parentActivity.finish();
        } else {
            Log.i("asdsa", String.valueOf(code));
        }
    }
}
