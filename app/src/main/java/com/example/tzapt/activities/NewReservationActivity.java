package com.example.tzapt.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tzapt.models.Account;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.tasks.DaysOffTask;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

public class NewReservationActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText peopleText;
    private EditText phoneText;

    private TextView nameLbl;
    private TextView emailLbl;
    private TextView peopleLbl;
    private TextView phoneLbl;

    private Button cancelBtn;
    private Button bookBtn;

    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.nameText);
        emailText= (EditText) findViewById(R.id.emailText);
        peopleText = (EditText) findViewById(R.id.peopleText);
        phoneText = (EditText) findViewById(R.id.phoneText);

        nameLbl = (TextView) findViewById(R.id.nameLbl);
        emailLbl = (TextView) findViewById(R.id.emailLbl);
        peopleLbl = (TextView) findViewById(R.id.peopleLbl);
        phoneLbl = (TextView) findViewById(R.id.phoneLbl);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        bookBtn = (Button) findViewById(R.id.bookBtn);

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                AsyncTask task = new DaysOffTask(NewReservationActivity.this);
                task.execute();
            }
        });

        Client client = new Client(new Account("asda", "parola123", "email@yahoo.com"), new PersonDetails("Victor", "Paun", "0767816361"));

        setFieldsFromClient(client);
    }

    public void setFieldsFromClient(Client client) {
        Account account = client.getAccount();
        PersonDetails details = client.getPersonDetails();

        nameText.setText(details.getFirstName() + " " + details.getLastName());
        emailText.setText(account.getEmail());
        phoneText.setText(details.getPhone());

        calendarView.setDateSelected(new Date(), true);
    }
}
