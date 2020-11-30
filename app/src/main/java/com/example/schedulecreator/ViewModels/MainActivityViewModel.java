package com.example.schedulecreator.ViewModels;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.Interfaces.PersonnelRepositoryListener;
import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.Models.ScheduleGeneratorSettings;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.repositories.PersonnelRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MainActivityViewModel extends ViewModel implements PersonnelRepoManager, SchedulerSettingsManager {

    private String DEBUG_TAG = getClass().getCanonicalName();

    private static ScheduleGeneratorSettings mScheduleGeneratorSettings;
    private PersonnelRepoManager mPersonnelRepoManager = PersonnelRepository.getInstance();


    public MainActivityViewModel(){
        super();
        //Initializing schedule creator settings
        mScheduleGeneratorSettings = new ScheduleGeneratorSettings();
        mPersonnelRepoManager.getObservableWorkersList().observeForever( new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                mScheduleGeneratorSettings.setPersonnelList(workers);
            }
        });

    }

    public ScheduleGeneratorSettings getScheduleGeneratorSettings() {
        return mScheduleGeneratorSettings;
    }


    @Override
    public void addWorker(Worker newWorker) {
        mPersonnelRepoManager.addWorker(newWorker);
        Log.d("test_tag", " Adding worker; first name: " + newWorker.getFirstName() + " last name: " + newWorker.getLastName());
    }

    @Override
    public LiveData<ArrayList<Worker>> getObservableWorkersList() {
        return mPersonnelRepoManager.getObservableWorkersList();
    }

    @Override
    public void updateWorker(Worker worker) {
        mPersonnelRepoManager.updateWorker(worker);

    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return false;
    }


    @Override
    public LiveData<Date> getStartDate() {
        return mScheduleGeneratorSettings.getStartDate();
    }

    @Override
    public LiveData<Date> getEndDate() {
        return mScheduleGeneratorSettings.getEndDate();
    }

    @Override
    public LiveData<ArrayList<Worker>> getSchedulerSettPersonnelList() {
        return mScheduleGeneratorSettings.getSchedulerSettPersonnelList();
    }

    @Override
    public void setStartDate(Date startDate) {
        mScheduleGeneratorSettings.setStartDate(startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        mScheduleGeneratorSettings.setEndDate(endDate);
    }
}
