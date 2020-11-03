package com.example.schedulecreator.Models;

import android.icu.util.Calendar;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;

//Shedule is used to store ordered list of scheduled days
public class Schedule {

    private ArrayList<ScheduledDay> mSchedule;

    public Schedule(){

        this.mSchedule = new ArrayList<>();
    }

    public void addDay( ScheduledDay day ){
        this.mSchedule.add( day );
    }

    public ArrayList<ScheduledDay> getSchedule() {
        return mSchedule;
    }

    private Date getInitialStartDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        return date;
    }

}
