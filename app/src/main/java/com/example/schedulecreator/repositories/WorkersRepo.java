package com.example.schedulecreator.repositories;

import android.net.wifi.WpsInfo;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Utils.RandomColors;
import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.database.Worker;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * WorkersRepo singleton.
 */
public class WorkersRepo {

    private MutableLiveData<ArrayList<Worker>> mWorkersList;
    private static AppDatabase mDb;


    private WorkersRepo(){
    }

    private static class Loader {
        static final WorkersRepo INSTANCE = new WorkersRepo();
    }

    public static WorkersRepo getInstance(){
        return Loader.INSTANCE;
    }

    public void getWorkersList( MutableLiveData<ArrayList<Worker>> workersList ){
        mWorkersList = workersList;
        initializeWorkersList();
    }

    public void initializeDatabase(){
        mDb = AppDatabase.getInstance();
        Log.d("antonio", "WorkersRepo; initializeDatabase(); mDb is null: " + Boolean.toString( mDb == null));
        ArrayList<Worker> workers = initializeWorkersListForDb();

        Observable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d("antonio", " WorkersRepo; initializeDatabase(); inserting workers... ");
                mDb.workerDao().insertWorkers( workers );
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void dbTest(final MutableLiveData<ArrayList<Worker>> workersList){
        mDb = AppDatabase.getInstance();
        Log.d("antonio_db", " WorkersRepo; dbTest; db is null: " + Boolean.toString( mDb == null));
        Single<List<Worker>> dbDataFetch = mDb.workerDao().getAll() ;

        dbDataFetch.subscribeOn(Schedulers.io()).
                observeOn( AndroidSchedulers.mainThread() ).
                subscribe(new DisposableSingleObserver<List<Worker>>() {
                    @Override
                    public void onSuccess(@NonNull List<Worker> workers) {
                        workersList.postValue( new ArrayList<Worker>(workers) );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("antonio", " WorkersRepo; dbTest; Single observable error: " + e.toString());
                        workersList.postValue(null);
                    }
                });

    }

    private ArrayList<Worker> initializeWorkersListForDb() {
        Log.d("test_tag_antonio", " initializeWorkersList(), repo hash code: " + hashCode());
        ArrayList<Worker> workers = new ArrayList<>();
        String[] workerNames = new String[]{
                "Nada,Bukovec",
                "Lidija,Gajski",
                "Carmen,Junačko",
                "Smiljka,Kovačić",
                "Emilija Katarina,Lozo",
                "Mateja,Ljubičić",
                "Mirna,Momčilović",
                "Kristina,Pliško",
                "Koraljka,Gobov Prstačić",
                "Maja,Radulović",
                "Anita,Šimić",
                "Jasmina,Matijašević Škerlj",
                "Nevenka,Valčić",

        };

        int i = 0;
        RandomColors colorGenerator = new RandomColors();
        for (String name : workerNames ){
//            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + name);
            String[] parts = name.split(",");
//            Log.d("test_tag_antonio", " parts:" + parts[1]);
            String firstName = parts[0];
            String lastName = parts[1];
//            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + firstName + " last name: " + lastName);
            Worker worker = new Worker( lastName, firstName);
            MutableLiveData<Boolean> selected = new MutableLiveData<>();
            selected.setValue( true );
            worker.setSelected( selected );
            worker.setColor( colorGenerator.getColor() );
            workers.add( worker );

        }

        Collections.sort( workers);
        return workers;

    }

    private void initializeWorkersList() {
        Log.d("test_tag_antonio", " initializeWorkersList(), repo hash code: " + hashCode());
        ArrayList<Worker> workers = new ArrayList<>();
        String[] workerNames = new String[]{
                "Nada,Bukovec",
                "Lidija,Gajski",
                "Carmen,Junačko",
                "Smiljka,Kovačić",
                "Emilija Katarina,Lozo",
                "Mateja,Ljubičić",
                "Mirna,Momčilović",
                "Kristina,Pliško",
                "Koraljka,Gobov Prstačić",
                "Maja,Radulović",
                "Anita,Šimić",
                "Jasmina,Matijašević Škerlj",
                "Nevenka,Valčić",

        };

        int i = 0;
        RandomColors colorGenerator = new RandomColors();
        for (String name : workerNames ){
//            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + name);
            String[] parts = name.split(",");
//            Log.d("test_tag_antonio", " parts:" + parts[1]);
            String firstName = parts[0];
            String lastName = parts[1];
//            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + firstName + " last name: " + lastName);
            Worker worker = new Worker( lastName, firstName);
            MutableLiveData<Boolean> selected = new MutableLiveData<>();
            selected.setValue( true );
            worker.setSelected( selected );
            worker.setColor( colorGenerator.getColor() );
            workers.add( worker );

            i++;

        }

        Collections.sort( workers);
//        mWorkersList.setValue( workers );

    }

}
