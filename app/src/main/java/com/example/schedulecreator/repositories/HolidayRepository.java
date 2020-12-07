package com.example.schedulecreator.repositories;

import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.schedulecreator.Utils.DataStore;
import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.database.HolidayDb;
import com.example.schedulecreator.holidayApi.Holiday;
import com.example.schedulecreator.holidayApi.HolidayApiClient;
import com.example.schedulecreator.holidayApi.HolidayApiInterface;
import com.example.schedulecreator.holidayApi.HolidayResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayRepository implements  HolidayRepoManager{

    private MutableLiveData<ArrayList<Holiday>> mHolidays;
    private SharedPreferences mSharedPreferences;
    private DataStore dataStore;
    private int nForwardYears = 4;


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
                db.close();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void refreshData() {
        AppDatabase db = AppDatabase.getInstance();
        db.holidayDbDao().deleteAll();

        HolidayApiInterface holidayInterface = HolidayApiClient.getClient().create(HolidayApiInterface.class);
        String apiKey = "82cde7fe8dab289874ce616896f8bcb6c4b9cda7";
        Calendar calendar = Calendar.getInstance();
        String year = null;

        ArrayList<Holiday> holidays = new ArrayList<>();
        List<HolidayDb> holidayDbs = new ArrayList<>();

        final int[] nY = {nForwardYears};
        for(int i=0; i<nForwardYears; i++){
            calendar.add(Calendar.YEAR, i);
            year = Integer.toString(calendar.get(Calendar.YEAR));
            Call<HolidayResponse> call = holidayInterface.getHolidays(apiKey, "hr", year);
            call.enqueue(new Callback<HolidayResponse>() {
                @Override
                public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                    for (Holiday holiday:holidays){
                        holidayDbs.add(new HolidayDb(holiday.getName(),
                                holiday.getDate().getDatetime().getDay(),
                                holiday.getDate().getDatetime().getMonth(),
                                holiday.getDate().getDatetime().getYear()));
                    }

                    nY[0]++;
                }

                @Override
                public void onFailure(Call<HolidayResponse> call, Throwable t) {

                }
            });
        }




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
