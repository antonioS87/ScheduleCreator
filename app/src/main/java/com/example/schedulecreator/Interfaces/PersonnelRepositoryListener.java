package com.example.schedulecreator.Interfaces;

import com.example.schedulecreator.Database.Worker;

import java.util.List;

public interface PersonnelRepositoryListener {
    void workersListChanged(List<Worker> workerList);
    void onWorkerAdded(Worker worker);
    void onWorkerUpdated(Worker worker);
    void onWorkerDeleted(Worker worker);
    void onSubscribe(List<Worker> workersList);
}
