package com.example.schedulecreator.repositories;

import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.schedulecreator.Utils.DataStore;
import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.holidayApi.Holiday;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HolidayRepository implements  HolidayRepoManager{

    private MutableLiveData<ArrayList<Holiday>> mHolidays;
    private SharedPreferences mSharedPreferences;
    private DataStore dataStore;


    private HolidayRepository(){
        initData();
    }

    private void initData() {
        dataStore = DataStore.getInstance(mSharedPreferences);
        dataStore.getDatabaseAge().observeForever(new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                //If the data in the database is older theen 2 days, request data over API
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE,-2);
                if(date != null && date.before(calendar.getTime())){
                    refreshData();
                }else {
                    getDataFromDb();
                }
            }
        });
    }

    private void getDataFromDb() {
        AppDatabase db = AppDatabase.getInstance();
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                ArrayList<Holiday> holidays = new ArrayList(db.holidayDbDao().getAll());
                mHolidays.setValue(holidays);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void refreshData() {

    }

    public static HolidayRepository getInstance(SharedPreferences sharedPreferences){
        return Loader.getInstance(sharedPreferences);
    }

    private static class Loader{
        private static HolidayRepository instance = new HolidayRepository();
        public static HolidayRepository getInstance(SharedPreferences sharedPreferences){
            instance.mSharedPreferences = sharedPreferences;
            return instance;
        }
    }

    @Override
    public LiveData<ArrayList<Holiday>> getHolidays() {
        return mHolidays;
    }


}
