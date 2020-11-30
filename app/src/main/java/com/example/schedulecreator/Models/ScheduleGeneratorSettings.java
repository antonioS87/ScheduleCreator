package com.example.schedulecreator.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleGeneratorSettings implements SchedulerSettingsManager {

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

    public LiveData<Date> getStartDate() {
        return mStartDate;
    }

    public LiveData<Date> getEndDate() {
        return mEndDate;
    }

    @Override
    public LiveData<ArrayList<Worker>> getSchedulerSettPersonnelList() {
        return mPersonnelList;
    }

    @Override
    public void setStartDate(Date startDate) {
        mStartDate.setValue(startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        mEndDate.setValue(endDate);
    }

    public void setPersonnelList(ArrayList<Worker> personnelList) {
        mPersonnelList.setValue(personnelList);
    }
}
