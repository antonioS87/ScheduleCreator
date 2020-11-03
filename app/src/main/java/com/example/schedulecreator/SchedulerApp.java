package com.example.schedulecreator;

import android.app.Application;

import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.repositories.WorkersRepo;

public class SchedulerApp extends Application {

    //Initializing database
    private AppDatabase db = AppDatabase.initialize( getApplicationContext() );
}
