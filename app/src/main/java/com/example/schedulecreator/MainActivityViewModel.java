package com.example.schedulecreator;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schedulecreator.Models.ScheduleGeneratorSettings;
import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.repositories.WorkersRepo;


public class MainActivityViewModel extends ViewModel {

    private String DEBUG_TAG = getClass().getCanonicalName();

    private ScheduleGeneratorSettings mScheduleGeneratorSettings;
    private WorkersRepo workersRepo = WorkersRepo.getInstance();

    public MainActivityViewModel(){
        super();

        Log.d(DEBUG_TAG, " contructor, object hashCode: " + hashCode());
        //Initializing schedule creator settings
        mScheduleGeneratorSettings = new ScheduleGeneratorSettings();
        refreshSchedulerCreatorPersonnelList();
    }

    private void refreshSchedulerCreatorPersonnelList() {
        Log.d("test_tag_antonio", " MainActivityViewModel hashCode: " + hashCode() + " workersRepo is null: " + Boolean.toString(workersRepo == null));
        WorkersRepo.getInstance().getWorkersList( mScheduleGeneratorSettings.getPersonnelList() );
        return;
    }


    public ScheduleGeneratorSettings getScheduleGeneratorSettings() {
        Log.d("test_tag_antonio", " MainActivityViewModel hashCode: " + hashCode() + " workersRepo is null: " + Boolean.toString(workersRepo == null));
        return mScheduleGeneratorSettings;
    }
}
