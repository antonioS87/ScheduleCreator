package com.example.schedulecreator;

import android.app.Application;

import com.example.schedulecreator.database.AppDatabase;

public class SchedulerApp extends Application {

    //Initializing database
    private static AppDatabase db;

    @Override
    public void onCreate() {
        db = AppDatabase.initialize( getApplicationContext() );
        super.onCreate();

    }




}
