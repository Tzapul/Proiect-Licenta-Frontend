package com.example.tzapt.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tzapt.decorators.CustomDayViewDecorator;
import com.example.tzapt.models.User;
import com.example.tzapt.tasks.GetDaysOffTask;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;
import java.util.HashSet;

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
            }
        });


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        setFieldsFromClient(null);

        AsyncTask getDaysOffTask = new GetDaysOffTask(this, calendarView);
        getDaysOffTask.execute();
    }

    public void setFieldsFromClient(User user) {
       /* Account account = user.getAccount();
        PersonDetails details = user.getPersonDetails();

        nameText.setText(details.getFirstName() + " " + details.getLastName());
        emailText.setText(account.getEmail());
        phoneText.setText(details.getPhone());*/

//        HashSet<CalendarDay> days = new HashSet<>();
//        days.add(new CalendarDay(new Date()));
//        DayViewDecorator decorator = new CustomDayViewDecorator(days);
//
//        calendarView.addDecorator(decorator);
    }
}
