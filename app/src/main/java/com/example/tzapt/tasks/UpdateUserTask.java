package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tzapt.models.Account;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by itsix on 27/06/2017.
 */

public class UpdateUserTask extends DefaultTask {

    private Client client;

    public UpdateUserTask(AppCompatActivity parentActivity, Client client) {
        super(parentActivity);
        this.client = client;
    }

    @Override
    protected String doInBackground(Object... params) {

        try {
            updateUser(params[0], params[1], params[2], params[3], params[4], params[5]);
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void updateUser(Object firstName, Object lastName, Object email, Object phoneNumber, Object password, Object id) throws UnsupportedEncodingException, JSONException {

        SyncHttpClient client = new SyncHttpClient();

        JSONObject params = new JSONObject();

        params.put("firstName", firstName.toString());
        params.put("lastName", lastName.toString());
        params.put("email", email.toString());
        params.put("phoneNumber", phoneNumber.toString());
        params.put("password", password.toString());

        StringEntity entity = new StringEntity(params.toString());

        client.addHeader("Content-Type", "application/json");
        client.put(parentActivity, requestUrl + "/user/update" + "/" + id.toString(), entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                code = i;
                response = new String(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                code = i;
                response = new String(bytes);
            }
        });
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (code == 200) {
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

                this.client = new User(id, account, personDetails);
                Toast.makeText(parentActivity, "Your account has been updated!", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
