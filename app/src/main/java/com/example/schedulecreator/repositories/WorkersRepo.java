package com.example.schedulecreator.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.Utils.RandomColors;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * WorkersRepo singleton.
 */
public class WorkersRepo {

    private MutableLiveData<ArrayList<Worker>> mWorkersList;


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
            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + name);
            String[] parts = name.split(",");
//            Log.d("test_tag_antonio", " parts:" + parts[1]);
            String firstName = parts[0];
            String lastName = parts[1];
            Log.d("test_tag_antonio", " initializeWorkersList(), name: " + firstName + " last name: " + lastName);
            Worker worker = new Worker( lastName, firstName, Integer.toString(i), true);
            worker.setColor( colorGenerator.getColor() );
            workers.add( worker );

            i++;

        }

        Collections.sort( workers);
        mWorkersList.setValue( workers );

    }

}
