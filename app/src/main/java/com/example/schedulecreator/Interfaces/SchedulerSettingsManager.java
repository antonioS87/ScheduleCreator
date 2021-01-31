package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.Database.Worker;

import java.util.ArrayList;
import java.util.Date;

public interface SchedulerSettingsManager {

    LiveData<Date> getStartDate();
    LiveData<Date> getEndDate();
    LiveData<ArrayList<Worker>> getSchedulerSettPersonnelList();
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);

}
