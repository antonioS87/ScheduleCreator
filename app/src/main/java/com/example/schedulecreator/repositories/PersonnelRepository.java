package com.example.schedulecreator.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.Interfaces.PersonnelRepositoryListener;
import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.database.Worker;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * WorkersRepo singleton.
 */
public class PersonnelRepository implements PersonnelRepoManager {

    private MutableLiveData<ArrayList<Worker>> mWorkersList;
    private static AppDatabase mDb;
    private List<PersonnelRepositoryListener> mPersonnelRepoListeners = new ArrayList<>();


    private PersonnelRepository(){
        mDb = AppDatabase.getInstance();
    }

    @Override
    public void addWorker(Worker worker) {
        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d("antonio", " WorkersRepo; adding worker... ");
                mDb.workerDao().insertWorker( worker );
                for(PersonnelRepositoryListener listener : mPersonnelRepoListeners){
                    listener.onWorkerAdded(worker);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }


    @Override
    public boolean updateWorker(Worker worker) {
        return false;
    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return false;
    }

    @Override
    public void addPersonnelRepositoryListener(PersonnelRepositoryListener personnelRepositoryListener) {
        mPersonnelRepoListeners.add(personnelRepositoryListener);


    }

    @Override
    public void requestWorkerListRefres() {

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
