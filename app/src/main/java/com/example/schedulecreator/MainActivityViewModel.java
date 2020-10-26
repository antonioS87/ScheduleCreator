package com.example.schedulecreator;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.Date;

public class MainActivityViewModel extends ViewModel {

    //Start and end date of the schedule
    private MutableLiveData<Date> startDate = new MutableLiveData<>();
    private MutableLiveData<Date> endDate = new MutableLiveData<>();

    public MainActivityViewModel(){
        super();

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


}