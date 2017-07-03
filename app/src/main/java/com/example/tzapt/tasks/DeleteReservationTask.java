package com.example.tzapt.tasks;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tzapt.adapters.MyReservationsAdapter;
import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.models.User;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.id;
import static cz.msebera.android.httpclient.client.methods.RequestBuilder.delete;

/**
 * Created by tzapt on 7/1/2017.
 */

public class DeleteReservationTask extends DefaultTask {

    private MyReservationsAdapter adapter;
    private Reservation resevationToDelete;

    public DeleteReservationTask(AppCompatActivity parentActivity, MyReservationsAdapter adapter) {
        super(parentActivity);
        this.adapter = adapter;
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

        deleteReservation(params[0]);

        return null;
    }

    private void deleteReservation(Object position) {
        int pos = (int) position;
        resevationToDelete = adapter.getItem(pos);

        SyncHttpClient client = new SyncHttpClient();

        client.delete(requestUrl + "/reservations/" + resevationToDelete.getId(), new AsyncHttpResponseHandler() {

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
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (code == 200) {
            adapter.remove(resevationToDelete);
            adapter.notifyDataSetChanged();
            Toast.makeText(parentActivity, "The booking was canceled.", Toast.LENGTH_SHORT).show();
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
