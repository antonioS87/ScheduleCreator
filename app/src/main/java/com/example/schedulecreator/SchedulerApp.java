package com.example.schedulecreator;

import android.app.Application;

import com.example.schedulecreator.Database.AppDatabase;
import com.example.schedulecreator.Repositories.HolidayRepository;

public class SchedulerApp extends Application {

    //Initializing database
    private static AppDatabase db;
    private static HolidayRepository holidayRepo;

    @Override
    public void onCreate() {

        //Initializing all classes that require context
        db = AppDatabase.initialize( getApplicationContext() );
        holidayRepo = HolidayRepository.getInstance(getApplicationContext());
        super.onCreate();

    }







}
