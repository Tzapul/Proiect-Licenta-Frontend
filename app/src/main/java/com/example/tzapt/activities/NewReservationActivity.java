package com.example.tzapt.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tzapt.helpers.Util;
import com.example.tzapt.models.Account;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.PersonDetails;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.models.User;
import com.example.tzapt.tasks.GetDaysOffAndDecorateTask;
import com.example.tzapt.tasks.SaveReservationTask;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class NewReservationActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText peopleText;
    private EditText phoneText;
    private EditText hourText;

    private TextView nameLbl;
    private TextView emailLbl;
    private TextView peopleLbl;
    private TextView phoneLbl;

    private Button cancelBtn;
    private Button bookBtn;

    private MaterialCalendarView calendarView;
    private Client client;

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
        hourText = (EditText) findViewById(R.id.hourText);

        nameLbl = (TextView) findViewById(R.id.nameLbl);
        emailLbl = (TextView) findViewById(R.id.phoneLbl);
        peopleLbl = (TextView) findViewById(R.id.peopleLbl);
        phoneLbl = (TextView) findViewById(R.id.emailLbl);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        bookBtn = (Button) findViewById(R.id.bookBtn);

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (selected) {
                    //TODO validate date selected higher than our date.
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NewReservationActivity.this.finish();
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //TODO validators

                AsyncTask makeReservation = new SaveReservationTask(NewReservationActivity.this);
                makeReservation.execute(new Reservation(0, nameText.getText().toString(), emailText.getText().toString(),
                        Util.formatDate(calendarView.getSelectedDate()), phoneText.getText().toString(),
                        Integer.valueOf(peopleText.getText().toString()), Integer.valueOf(hourText.getText().toString()),
                        null), client.getId());
            }
        });


        Intent intent = this.getIntent();
        client = (Client) intent.getSerializableExtra("client");
        if (client instanceof User) {
            setFieldsFromClient((User)client);
        }

        AsyncTask getDaysOffTask = new GetDaysOffAndDecorateTask(this, calendarView);
        getDaysOffTask.execute();
    }

    public void setFieldsFromClient(User user) {
        Account account = user.getAccount();
        PersonDetails details = user.getPersonDetails();

        nameText.setText(details.getFirstName() + " " + details.getLastName());
        emailText.setText(account.getEmail());
        phoneText.setText(details.getPhone());

    }
}
