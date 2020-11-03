package com.example.schedulecreator.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WorkerDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkerDbDao workerDao();
    private static AppDatabase mInstance;


    public static AppDatabase initialize(Context context){

        synchronized (AppDatabase.class) {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context,
                        AppDatabase.class, "database-name").build();


            }
        }
        return mInstance;
    }

    public static AppDatabase getInstance(){
        return mInstance;
    }
}

