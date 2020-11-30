package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;
import java.util.Date;

public interface SchedulerSettingsManager {

    LiveData<Date> getStartDate();
    LiveData<Date> getEndDate();
    LiveData<ArrayList<Worker>> getSchedulerSettPersonnelList();
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);

}
