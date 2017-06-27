package com.example.tzapt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tzapt.activities.R;
import com.example.tzapt.adapters.MyReservationsAdapter;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingsFragment extends Fragment {

    private View view;
    private ListView reservationListView;
    private MyReservationsAdapter myReservationsAdapter;
    private User client;

    public BookingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookings, container, false);
        reservationListView = (ListView) view.findViewById(R.id.reservations_list_view);
        myReservationsAdapter = new MyReservationsAdapter(getActivity(), R.layout.row_reservation);
        reservationListView.setAdapter(myReservationsAdapter);

        Reservation dummy1 = new Reservation(1, "Andrei", "email","date", "phone", 4, null);
        Reservation dummy2 = new Reservation(1, "Andrei", "email","date", "phone", 4, null);
        Reservation dummy3 = new Reservation(1, "Andrei", "email","date", "phone", 4, null);

        myReservationsAdapter.add(dummy1);
        myReservationsAdapter.add(dummy2);
        myReservationsAdapter.add(dummy3);

        return view;
    }

}
