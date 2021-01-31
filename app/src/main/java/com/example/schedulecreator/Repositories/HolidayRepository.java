package com.example.schedulecreator.Repositories;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.schedulecreator.Utils.DataStore;
import com.example.schedulecreator.Database.AppDatabase;
import com.example.schedulecreator.Database.HolidayDb;
import com.example.schedulecreator.HolidayApi.Holiday;
import com.example.schedulecreator.HolidayApi.HolidayApiClient;
import com.example.schedulecreator.HolidayApi.HolidayApiInterface;
import com.example.schedulecreator.HolidayApi.HolidayResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayRepository implements  HolidayRepoManager{

    private MutableLiveData<ArrayList<HolidayDb>> mHolidays;
    private int nForwardYears = 4;
    private String DEBUG_TAG = "test_holiday_tagc";
    private Context mContext;
    private MutableLiveData<HolidayFetchStatus> fetchStatus = new MutableLiveData<>();


    private HolidayRepository(Context context){
        mContext = context;
        fetchStatus.observeForever(new Observer<HolidayFetchStatus>() {
            @Override
            public void onChanged(HolidayFetchStatus holidayFetchStatus) {
                if(holidayFetchStatus == HolidayFetchStatus.DONE){
                    getDataFromDb();
                }
            }
        });
        fetchStatus.setValue(HolidayFetchStatus.PENDING);
        initData();
    }

    private void initData() {
        Log.d(DEBUG_TAG, " Initializing HolidayRepository");
        mHolidays = new MutableLiveData<>();
        Calendar calendar = Calendar.getInstance();

        //When database age is fetched check if the holidays need to be fetched from the server
        //or database values can be use
        Date dbAge = DataStore.getInstance(mContext).getDatabaseAge();
        Log.d(DEBUG_TAG, " Holiday database age: " + dbAge);
        if(dbAge == null || dbAge.before(calendar.getTime())){
            Log.d(DEBUG_TAG, " Refreshing holiday database... ");
            refreshData();
        }else {
            Log.d(DEBUG_TAG, " Getting data from holiday database... ");

            //No need to fetch holidays from server so the fetch status can be set to done
            fetchStatus.setValue(HolidayFetchStatus.DONE);
        }



    }

    private void getDataFromDb() {
        AppDatabase db = AppDatabase.getInstance();
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                ArrayList<HolidayDb> holidays = new ArrayList(db.holidayDbDao().getAll());

                if(holidays != null){
                    mHolidays.postValue(holidays);
                }

            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void writeHolidaysToDb(ArrayList<HolidayDb> holidayDbs) {
        AppDatabase db = AppDatabase.getInstance();
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d(DEBUG_TAG, " Writing holidays to database; database is open: "  + db.isOpen());
                if(holidayDbs != null && db.isOpen()){
                    //Write new holiday values
                    db.holidayDbDao().insertHolidays(holidayDbs);
                    mHolidays.postValue(holidayDbs);
                }

            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void clearHolidayDatabase() {
        AppDatabase db = AppDatabase.getInstance();
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d(DEBUG_TAG, " Clearing holidays database; database is open: "  + db.isOpen());
                if( db.isOpen()){
                    //Clean the database
                    db.holidayDbDao().deleteAll();
                }

            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    private void refreshData() {

        //TODO check network availability before clearing the database
        clearHolidayDatabase();

        HolidayApiInterface holidayInterface = HolidayApiClient.getClient().create(HolidayApiInterface.class);
        String apiKey = "82cde7fe8dab289874ce616896f8bcb6c4b9cda7";
        Calendar calendar = Calendar.getInstance();
        String year = null;

        for(int i=0; i<nForwardYears; i++) {
            calendar.add(Calendar.YEAR, i);
            year = Integer.toString(calendar.get(Calendar.YEAR));
            Call<HolidayResponse> holidayRequest = holidayInterface.getHolidays(apiKey, "hr", year, "national");

            holidayRequest.enqueue(new Callback<HolidayResponse>() {
                @Override
                public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                    ArrayList<HolidayDb> holidayDbs = new ArrayList<>();

                    for (Holiday holiday : response.body().getResponse().getHolidays()){
                        holidayDbs.add(new HolidayDb(holiday.getName(),
                                holiday.getDate().getDatetime().getDay(),
                                holiday.getDate().getDatetime().getMonth(),
                                holiday.getDate().getDatetime().getYear()));
                    }

                    //Put the data into the database
                    writeHolidaysToDb(holidayDbs);

                }

                @Override
                public void onFailure(Call<HolidayResponse> call, Throwable t) {
                    Log.d(DEBUG_TAG, " Fetching holiday data failed");
                }
            });

        }

    }

    public static HolidayRepository getInstance(){
        return Loader.getInstance();
    }

    public static HolidayRepository getInstance(Context context){
        return Loader.getInstance( context );
    }

    private static class Loader{
        private static HolidayRepository instance = null;

        public static HolidayRepository getInstance(){
            return instance;
        }

        public static HolidayRepository getInstance(Context context){
            instance = new HolidayRepository(context);
            return instance;
        }
    }

    @Override
    public LiveData<ArrayList<HolidayDb>> getHolidays() {
        return mHolidays;
    }

    private enum HolidayFetchStatus{
        PENDING,
        DONE
    }



}
