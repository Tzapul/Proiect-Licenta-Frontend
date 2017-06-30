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
import com.example.tzapt.models.ScheduleDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itsix on 30/06/2017.
 */

public class ScheduleDayAdapeter extends ArrayAdapter<ScheduleDay> {

    private List<ScheduleDay> list = new ArrayList<>();

    public ScheduleDayAdapeter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(ScheduleDay scheduleDay) {
        super.add(scheduleDay);
        list.add(scheduleDay);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ScheduleDay getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        row = convertView;
        final DaysOffHolder scheduleDayHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_reservation, parent, false);
            scheduleDayHolder = new ScheduleDayAdapeter.DaysOffHolder();


            scheduleDayHolder.summaryText = (TextView) row.findViewById(R.id.reservation_summary);
            scheduleDayHolder.deleteBtn = (Button) row.findViewById(R.id.delete_reservation_btn);
            scheduleDayHolder.dateText = (TextView) row.findViewById(R.id.number_of_people);

            row.setTag(scheduleDayHolder);

        } else {
            scheduleDayHolder = (ScheduleDayAdapeter.DaysOffHolder) row.getTag();
        }

        final ScheduleDay scheduleDay = (ScheduleDay) this.getItem(position);
        scheduleDayHolder.summaryText.setText(scheduleDay.getDay());

        scheduleDayHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
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
    public void remove(@Nullable ScheduleDay object) {
        super.remove(object);
        list.remove(object);
    }

    public void clearData(){
        list.clear();
    }

    public List<ScheduleDay> getList() {
        return list;
    }


}
