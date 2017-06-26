package com.example.tzapt.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tzapt.activities.R;
import com.example.tzapt.models.Reservation;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by tzapt on 6/26/2017.
 */

public class MyReservationsAdapter extends ArrayAdapter<Reservation> {

    private List<Reservation> reservations = new ArrayList<>();


    public MyReservationsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Reservation reservation) {
        super.add(reservation);
        reservations.add(reservation);
    }

    @Override
    public int getCount() {
        return reservations.size();
    }

    @Override
    public Reservation getItem(int position) {
        return reservations.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        row = convertView;
        final ReservationHolder productHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_reservation, parent, false);
            productHolder = new MyReservationsAdapter.ReservationHolder();


            productHolder.text_summary = (TextView) row.findViewById(R.id.reservation_summary);
            productHolder.btn_delete = (Button) row.findViewById(R.id.delete_reservation_btn);
            productHolder.tx_number_of_people = (TextView) row.findViewById(R.id.number_of_people);

            row.setTag(productHolder);

        } else {
            productHolder = (MyReservationsAdapter.ReservationHolder) row.getTag();
        }

        final Reservation reservation = (Reservation) this.getItem(position);
        productHolder.text_summary.setText(reservation.getName());
        productHolder.tx_number_of_people.setText("Number of people " + reservation.getPeople());

        productHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // myOrderFragment.removeProduct(reservation);
            }
        });

        return row;
    }

    static class ReservationHolder {
        TextView text_summary;
        Button btn_delete;
        TextView tx_number_of_people;
    }

    @Override
    public void remove(@Nullable Reservation object) {
        super.remove(object);
        reservations.remove(object);
    }

    public void clearData(){
        reservations.clear();
    }

    public List<Reservation> getList() {
        return reservations;
    }

}
