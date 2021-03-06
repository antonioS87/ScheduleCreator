package com.example.schedulecreator.Database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Worker.class, HolidayDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;
    private static final String dbName = "scDb";


    public static AppDatabase initialize(Context context){
        Log.d("antonio_db", " AppDatabase initialization start");
        synchronized (AppDatabase.class) {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context,
                        AppDatabase.class, dbName).
                        build();


            }
        }
        Log.d("antonio_db", " AppDatabase initialization done");
        return mInstance;
    }

    public abstract WorkerDbDao workerDao();
    public abstract HolidayDbDao holidayDbDao();
    public static AppDatabase getInstance(){
        return mInstance;
    }
}

