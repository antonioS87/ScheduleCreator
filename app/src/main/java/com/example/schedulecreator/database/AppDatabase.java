package com.example.schedulecreator.database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Worker.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkerDbDao workerDao();
    private static AppDatabase mInstance;


    public static AppDatabase initialize(Context context){
        Log.d("antonio", " AppDatabase initialization start");
        synchronized (AppDatabase.class) {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context,
                        AppDatabase.class, "database-name").build();


            }
        }
        Log.d("antonio", " AppDatabase initialization done");
        return mInstance;
    }

    public static AppDatabase getInstance(){
        return mInstance;
    }
}

