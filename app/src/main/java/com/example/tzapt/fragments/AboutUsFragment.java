package com.example.tzapt.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.tzapt.activities.R;
import com.example.tzapt.tasks.GetScheduleTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    private TextView phoneText;
    private TextView emailText;
    private TableLayout daysTable;
    private View view;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_us, container, false);

        phoneText = (TextView) view.findViewById(R.id.phoneText);
        emailText = (TextView) view.findViewById(R.id.emailText);
        daysTable = (TableLayout) view.findViewById(R.id.daysTable);


        AsyncTask getScheduleTask = new GetScheduleTask((AppCompatActivity)getActivity(), daysTable);
        getScheduleTask.execute();

        return view;
    }

}
