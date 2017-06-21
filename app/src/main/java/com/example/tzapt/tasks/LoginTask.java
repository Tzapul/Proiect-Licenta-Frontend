package com.example.tzapt.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tzapt.activities.LoginActivity;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import static android.R.attr.password;

/**
 * Created by tzapt on 6/19/2017.
 */

public class LoginTask extends AsyncTask<Void, Void, String> {

    private String username;
    private String password;
    private String requestUrl;
    private String message;

    public LoginTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        requestUrl = "http://192.168.1.103:10284";
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            sendPost();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void sendPost() throws IOException, JSONException, SocketTimeoutException {
        Log.i("asdasd", "test");
        URL url = new URL(requestUrl + "/login"); //Enter URL here

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
        httpURLConnection.connect();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", username);
        jsonObject.put("password", password);

        Log.i("asdasd", "test");

        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
        wr.writeBytes(jsonObject.toString());
        wr.flush();
        wr.close();

        InputStream in = httpURLConnection.getInputStream();
        message = in.toString();
        Log.d("asd", in.toString());

    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(message);
        if(message == "200") {
            //TODO go to the next activity
        } else {
            //TODO make a toast
        }
    }
}
