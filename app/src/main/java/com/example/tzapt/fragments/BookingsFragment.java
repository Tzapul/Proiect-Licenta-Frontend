package com.example.tzapt.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.tzapt.activities.NewReservationActivity;
import com.example.tzapt.activities.R;
import com.example.tzapt.activities.UserMainView;
import com.example.tzapt.adapters.MyReservationsAdapter;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.models.User;
import com.example.tzapt.tasks.GetReservatoinTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingsFragment extends Fragment {

    private View view;
    private ListView reservationListView;
    private MyReservationsAdapter myReservationsAdapter;
    private Button addButton;

    private Client client;

    public BookingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookings, container, false);
        reservationListView = (ListView) view.findViewById(R.id.reservations_list_view);
        myReservationsAdapter = new MyReservationsAdapter(getActivity(), R.layout.row_reservation);
        reservationListView.setAdapter(myReservationsAdapter);

        reservationListView.setEmptyView(view.findViewById(R.id.empty_view));
        addButton = (Button) view.findViewById(R.id.addButton);


        client = (Client) getActivity().getIntent().getSerializableExtra("client");

        AsyncTask getReservationsTask = new GetReservatoinTask((AppCompatActivity) getActivity(), myReservationsAdapter, client);
        getReservationsTask.execute();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openNewBookingActivity();
            }
        });

        return view;
    }

    private void openNewBookingActivity() {
        Intent intent = new Intent(getActivity(), NewReservationActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("client", client);
        intent.putExtras(b);
        startActivity(intent);

    }

}
