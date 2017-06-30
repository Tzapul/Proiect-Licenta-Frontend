package com.example.tzapt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tzapt.activities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaysOffFragment extends Fragment {

    View view;

    public DaysOffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_days_off, container, false);

        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);

        return view;
    }

}
