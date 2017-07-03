package com.example.tzapt.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.tzapt.activities.R;
import com.example.tzapt.adapters.ScheduleDayAdapter;
import com.example.tzapt.tasks.GetScheduleDaysTask;

public class ScheduleFragment extends Fragment {

    private View view;
    private ListView daysOffListView;
    private ScheduleDayAdapter scheduleDayAdapeter;
    private Button addButton;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_days_off, container, false);

        daysOffListView = (ListView) view.findViewById(R.id.scheduleDay_list_view);
        scheduleDayAdapeter = new ScheduleDayAdapter(getActivity(), R.layout.row_reservation);
        daysOffListView.setAdapter(scheduleDayAdapeter);

        daysOffListView.setEmptyView(view.findViewById(R.id.empty_view));

        addButton = (Button) view.findViewById(R.id.add_schedule_day);

        AsyncTask task = new GetScheduleDaysTask((AppCompatActivity)getActivity(), scheduleDayAdapeter);
        task.execute();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //openNewBookingActivity();
            }
        });

        return view;
    }

}
