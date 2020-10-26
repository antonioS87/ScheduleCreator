package com.example.schedulecreator.Models;

import android.icu.util.Calendar;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;

//Shedule is used to store ordered list of scheduled days
public class Schedule {

    private Date mStartDate;
    private Date mEndDate;

    private ArrayList<Day> schedule;

    public Schedule(){

        this.mStartDate = getInitialStartDate();
        this.mEndDate = null;
        this.schedule = new ArrayList<>();
    }

    public void addDay( Day day ){
        this.schedule.add( day );
    }

    public ArrayList<Day> getSchedule() {
        return schedule;
    }


    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        this.mStartDate = startDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        this.mEndDate = endDate;
    }

    private Date getInitialStartDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        return date;
    }

}
