package com.example.schedulecreator;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.repositories.WorkersRepo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivityViewModel extends ViewModel {

    private String DEBUG_TAG = getClass().getCanonicalName();

    //Start and end date of the schedule
    private MutableLiveData<Date> startDate = new MutableLiveData<>();
    private MutableLiveData<Date> endDate = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Worker>> mPersonnelList = new MutableLiveData<>();
    private WorkersRepo workersRepo = WorkersRepo.getInstance();

    //List of personnel
    private MutableLiveData<Worker> personnelList = new MutableLiveData<>();

    public MainActivityViewModel(){
        super();
        Log.d(DEBUG_TAG, " contructor, object hashCode: " + hashCode());
        //Initializing dates
        Calendar c = Calendar.getInstance();
        startDate.setValue( c.getTime() );
        c.add( Calendar.MONTH, 1);
        endDate.setValue( c.getTime() );

    }

    public MutableLiveData<Date> getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate.postValue( startDate );
    }

    public MutableLiveData<Date> getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate.postValue( endDate );
    }


    public MutableLiveData<ArrayList<Worker>> getPersonnelList() {
        Log.d("test_tag_antonio", " MainActivityViewModel hashCode: " + hashCode() + " workersRepo is null: " + Boolean.toString(workersRepo == null));
        if( mPersonnelList.getValue() == null) {
            WorkersRepo.getInstance().getWorkersList(mPersonnelList);
        }
        return mPersonnelList;
    }
}
