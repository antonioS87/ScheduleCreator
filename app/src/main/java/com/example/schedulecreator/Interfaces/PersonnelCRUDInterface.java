package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public interface PersonnelCRUDInterface {
    void addWorker(Worker worker);
    void getAllWorkers(LiveData<ArrayList<Worker>> workersList);
    boolean updateWorker(Worker worker);
    boolean deleteWorker(Worker worker);
    void addPersonnelRepositoryListener(PersonnelRepositoryListener personnelRepositoryListener);
}
