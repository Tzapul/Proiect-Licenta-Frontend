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
import com.example.tzapt.models.DayOff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsix on 30/06/2017.
 */

public class DaysOffAdapter extends ArrayAdapter<DayOff> {

    private List<DayOff> list = new ArrayList<>();

    public DaysOffAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(DayOff dayOff) {
        super.add(dayOff);
        list.add(dayOff);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DayOff getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        row = convertView;
        final DaysOffHolder daysOffHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_reservation, parent, false);
            daysOffHolder = new DaysOffAdapter.DaysOffHolder();


            daysOffHolder.summaryText = (TextView) row.findViewById(R.id.reservation_summary);
            daysOffHolder.deleteBtn = (Button) row.findViewById(R.id.delete_reservation_btn);
            daysOffHolder.dateText = (TextView) row.findViewById(R.id.number_of_people);

            row.setTag(daysOffHolder);

        } else {
            daysOffHolder = (DaysOffAdapter.DaysOffHolder) row.getTag();
        }

        final DayOff dayOff = (DayOff) this.getItem(position);
        daysOffHolder.summaryText.setText(dayOff.getDate());

        daysOffHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // myOrderFragment.removeProduct(reservation);
            }
        });

        return row;
    }

    static class DaysOffHolder {
        TextView summaryText;
        Button deleteBtn;
        TextView dateText;
    }

    @Override
    public void remove(@Nullable DayOff object) {
        super.remove(object);
        list.remove(object);
    }

    public void clearData(){
        list.clear();
    }

    public List<DayOff> getList() {
        return list;
    }
}
