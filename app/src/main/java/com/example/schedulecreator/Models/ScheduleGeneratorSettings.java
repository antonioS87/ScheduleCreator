package com.example.schedulecreator.Models;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleGeneratorSettings {

    //Start and end date of the schedule
    private MutableLiveData<Date> mStartDate;
    private MutableLiveData<Date> mEndDate;
    private MutableLiveData<ArrayList<Worker>> mPersonnelList;

    public ScheduleGeneratorSettings( ){
        mStartDate = new MutableLiveData<Date>();
        mEndDate = new MutableLiveData<Date>();
        mPersonnelList = new MutableLiveData<ArrayList<Worker>>();
        Calendar c = Calendar.getInstance();
        mStartDate.setValue( c.getTime() );
        c.add( Calendar.MONTH, 1);
        mEndDate.setValue( c.getTime() );
    }

    public MutableLiveData<Date> getStartDate() {
        return mStartDate;
    }

    public MutableLiveData<Date> getEndDate() {
        return mEndDate;
    }

    public MutableLiveData<ArrayList<Worker>> getPersonnelList() {
        return mPersonnelList;
    }

    public void setPersonnelList(MutableLiveData<ArrayList<Worker>> personnelList) {
        mPersonnelList = personnelList;
    }
}
