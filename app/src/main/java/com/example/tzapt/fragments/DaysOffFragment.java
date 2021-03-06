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
import com.example.tzapt.adapters.DaysOffAdapter;
import com.example.tzapt.tasks.GetDaysOffAndDecorateTask;
import com.example.tzapt.tasks.GetDaysOffTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysOffFragment extends Fragment {

    private View view;
    private ListView daysOffListView;
    private DaysOffAdapter daysOffAdapter;
    private Button addButton;

    public DaysOffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_days_off, container, false);

        daysOffListView = (ListView) view.findViewById(R.id.daysOff_list_view);
        daysOffAdapter = new DaysOffAdapter(getActivity(), R.layout.row_reservation);
        daysOffListView.setAdapter(daysOffAdapter);

        daysOffListView.setEmptyView(view.findViewById(R.id.empty_view));

        addButton = (Button) view.findViewById(R.id.add_day_off);

        AsyncTask task = new GetDaysOffTask((AppCompatActivity)getActivity(), daysOffAdapter);
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
