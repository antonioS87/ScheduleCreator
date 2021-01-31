package com.example.schedulecreator.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.Database.AppDatabase;
import com.example.schedulecreator.Database.Worker;


import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * WorkersRepo singleton.
 */
public class PersonnelRepository implements PersonnelRepoManager {

    private MutableLiveData<ArrayList<Worker>> mWorkersList;
    private static AppDatabase mDb;


    private PersonnelRepository(){
        mDb = AppDatabase.getInstance();
        mWorkersList = new MutableLiveData<>();
        getWorkersFromDB();
    }


    @Override
    public void addWorker(Worker worker) {
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d("worker", " WorkersRepo; adding worker... ");
                mDb.workerDao().insertWorker( worker );
                mWorkersList.getValue().add(worker);
                mWorkersList.postValue(mWorkersList.getValue());
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }

    private void getWorkersFromDB(){
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d("worker", " WorkersRepo; getting workers from db... ");
                mWorkersList.postValue(new ArrayList<>( mDb.workerDao().getAll()) );
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }


    @Override
    public void updateWorker(Worker worker) {
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d("worker", " WorkersRepo; adding worker... ");
                mDb.workerDao().updateWorker( worker );
                mWorkersList.postValue(new ArrayList<>(mDb.workerDao().getAll()));
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return false;
    }


    @Override
    public LiveData<ArrayList<Worker>> getObservableWorkersList() {
        return mWorkersList;
    }


    private static class Loader {
        static final PersonnelRepository INSTANCE = new PersonnelRepository();
    }

    public static PersonnelRepository getInstance(){
        return Loader.INSTANCE;
    }
//
//                "Nada,Bukovec",
//                "Lidija,Gajski",
//                "Carmen,Junačko",
//                "Smiljka,Kovačić",
//                "Emilija Katarina,Lozo",
//                "Mateja,Ljubičić",
//                "Mirna,Momčilović",
//                "Kristina,Pliško",
//                "Koraljka,Gobov Prstačić",
//                "Maja,Radulović",
//                "Anita,Šimić",
//                "Jasmina,Matijašević Škerlj",
//                "Nevenka,Valčić",



}
