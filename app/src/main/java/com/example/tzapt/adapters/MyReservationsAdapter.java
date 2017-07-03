package com.example.tzapt.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tzapt.activities.R;
import com.example.tzapt.models.Reservation;
import com.example.tzapt.tasks.DeleteReservationTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzapt on 6/26/2017.
 */

public class MyReservationsAdapter extends ArrayAdapter<Reservation> {

    private List<Reservation> list = new ArrayList<>();

    public MyReservationsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Reservation reservation) {
        super.add(reservation);
        list.add(reservation);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Reservation getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;

        row = convertView;
        final ReservationHolder reservationHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_reservation, parent, false);
            reservationHolder = new MyReservationsAdapter.ReservationHolder();


            reservationHolder.text_summary = (TextView) row.findViewById(R.id.reservation_summary);
            reservationHolder.btn_delete = (Button) row.findViewById(R.id.delete_reservation_btn);
            reservationHolder.tx_number_of_people = (TextView) row.findViewById(R.id.number_of_people);

            row.setTag(reservationHolder);

        } else {
            reservationHolder = (MyReservationsAdapter.ReservationHolder) row.getTag();
        }

        final Reservation reservation = (Reservation) this.getItem(position);
        reservationHolder.text_summary.setText(reservation.getName());
        reservationHolder.tx_number_of_people.setText("Number of people " + reservation.getPeople());

        reservationHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask removeTask = new DeleteReservationTask((AppCompatActivity)getContext(), MyReservationsAdapter.this);
                removeTask.execute(position);
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
        list.remove(object);
    }

    public void clearData(){
        list.clear();
    }

    public List<Reservation> getList() {
        return list;
    }

}
