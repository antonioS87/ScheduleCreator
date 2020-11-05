package com.example.schedulecreator;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schedulecreator.Models.ScheduleGeneratorSettings;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.repositories.WorkersRepo;

import java.util.ArrayList;


public class MainActivityViewModel extends ViewModel {

    private String DEBUG_TAG = getClass().getCanonicalName();

    private ScheduleGeneratorSettings mScheduleGeneratorSettings;
    private WorkersRepo mWorkersRepo = WorkersRepo.getInstance();

    public MainActivityViewModel(){
        super();


        Log.d(DEBUG_TAG, " contructor, object hashCode: " + hashCode());
        //Initializing schedule creator settings
        mScheduleGeneratorSettings = new ScheduleGeneratorSettings();
        refreshSchedulerCreatorPersonnelList();
    }

    public MutableLiveData<ArrayList<Worker>> testDb(){
        MutableLiveData<ArrayList<Worker>> testList = new MutableLiveData<>();

        mWorkersRepo.dbTest(testList );
        return testList;
    }

    private void refreshSchedulerCreatorPersonnelList() {
        Log.d("test_tag_antonio", " MainActivityViewModel hashCode: " + hashCode() + " workersRepo is null: " + Boolean.toString(mWorkersRepo == null));
        WorkersRepo.getInstance().getWorkersList( mScheduleGeneratorSettings.getPersonnelList() );
        return;
    }


    public ScheduleGeneratorSettings getScheduleGeneratorSettings() {
        Log.d("test_tag_antonio", " MainActivityViewModel hashCode: " + hashCode() + " workersRepo is null: " + Boolean.toString(mWorkersRepo == null));
        return mScheduleGeneratorSettings;
    }

    //Get rid of this
    public void initializeDatabase(){
        mWorkersRepo.initializeDatabase();
    }
}
