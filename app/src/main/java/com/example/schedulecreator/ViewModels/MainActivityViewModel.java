package com.example.schedulecreator.ViewModels;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.Interfaces.PersonnelRepositoryListener;
import com.example.schedulecreator.Models.ScheduleGeneratorSettings;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.repositories.PersonnelRepository;

import java.util.ArrayList;
import java.util.List;


public class MainActivityViewModel extends ViewModel implements PersonnelManager, PersonnelRepositoryListener {

    private String DEBUG_TAG = getClass().getCanonicalName();

    private static ScheduleGeneratorSettings mScheduleGeneratorSettings;
    private PersonnelRepoManager mPersonnelRepoManager = PersonnelRepository.getInstance();

    public MainActivityViewModel(){
        super();
        //Initializing schedule creator settings
        mScheduleGeneratorSettings = new ScheduleGeneratorSettings();
        refreshSchedulerCreatorPersonnelList();
    }

    private void refreshSchedulerCreatorPersonnelList() {
        return;
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
    public void getObservableWorkersList(LiveData<ArrayList<Worker>> workersList) {

    }

    @Override
    public boolean updateWorker(Worker worker) {
        return false;
    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return false;
    }

    @Override
    public void workersListChanged(List<Worker> workerList) {

    }

    @Override
    public void onWorkerAdded(Worker worker) {
        Log.d("test_tag", " MainActivityViewModel repo listener: worker added: " + worker.getFirstName() + " " + worker.getLastName());
    }

    @Override
    public void onWorkerUpdated(Worker worker) {

    }

    @Override
    public void onWorkerDeleted(Worker worker) {

    }

    @Override
    public void onSubscribe(List<Worker> workersList) {

    }
}
