package com.example.schedulecreator.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.SchedulerApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataStore {

    public static String SCHEMA_NAME = "data_store";
    private Date databaseAge = null;
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");





    public static DataStore getInstance(Context context){
        Loader.getInstance().sharedPreferences = context.getSharedPreferences(SCHEMA_NAME, Context.MODE_PRIVATE);
        Loader.getInstance().iniData();
        return Loader.getInstance();
    }

    private static class Loader{
        private static DataStore dataStore = new DataStore();
        public static DataStore getInstance(){
            return dataStore;
        }
    }

    public Date getDatabaseAge() {
        try {
            databaseAge = simpleDateFormat.parse(sharedPreferences.getString(Key.DATABASE_AGE.toString(), ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return databaseAge;
    }

    public void setDatabaseAge(Date rdatabaseAge) {

        String newDbAge = simpleDateFormat.format(rdatabaseAge);
        sharedPreferences.edit().putString(Key.DATABASE_AGE.toString(), newDbAge);
        databaseAge = rdatabaseAge;
//        Observable.fromRunnable(new Runnable() {
//            @Override
//            public void run() {
//                String newDbAge = simpleDateFormat.format(rdatabaseAge);
//                sharedPreferences.edit().putString(Key.DATABASE_AGE.toString(), newDbAge);
//                databaseAge.postValue(rdatabaseAge);
//            }
//        }).subscribeOn(Schedulers.io()).subscribe();

    }

    private void iniData(){

        try {
            databaseAge = simpleDateFormat.parse(sharedPreferences.getString(Key.DATABASE_AGE.toString(), ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Observable.fromRunnable(new Runnable() {
//            @Override
//            public void run() {
//                Date dbAge = null;
//                try {
//                    dbAge = simpleDateFormat.parse(sharedPreferences.getString(Key.DATABASE_AGE.toString(), ""));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                databaseAge.setValue(dbAge);
//
//            }
//        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public enum Key{
        DATABASE_AGE
    }

}
