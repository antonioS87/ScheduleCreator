package com.example.schedulecreator.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Models.Worker;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * WorkersRepo singleton.
 */
public class WorkersRepo {

    private MutableLiveData<ArrayList<Worker>> workersList;


    private WorkersRepo(){
        this.workersList = new MutableLiveData<>();
        initializeWorkersList();
    }

    private static class Loader {
        static final WorkersRepo INSTANCE = new WorkersRepo();
    }

    public static WorkersRepo getInstance(){
        return Loader.INSTANCE;
    }

    public MutableLiveData<ArrayList<Worker>> getWorkersList(){
        return workersList;
    }

    private void initializeWorkersList() {
        String[] workeNames = new String[]{
        "Nada Bukovec",
        "Lidija Gajski",
        "Carmen Junačko",
        "Smiljka Kovačić",
        "Emilija Katarina Lozo",
        "Mateja Ljubičić",
        "Mirna Momčilović",
        "Kristina Pliško",
        "Koraljka Gobov Prstačić",
        "Maja Radulović",
        "Anita Šimić",
        "Jasmina Matijašević Škerlj",
        "Nevenka Valčić"
        };

        int i = 0;
        for (String name : workeNames ){

            workersList.getValue().add(
                    new Worker( name, Integer.toString(i))
            );

            i++;

        }

    }

}
