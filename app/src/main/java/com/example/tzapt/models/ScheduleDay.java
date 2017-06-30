package com.example.tzapt.models;

/**
 * Created by itsix on 30/06/2017.
 */

public class ScheduleDay {

    private String day;

    private int start;

    private int end;

    public ScheduleDay(String day, int start, int end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
