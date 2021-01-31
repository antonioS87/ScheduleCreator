package com.example.schedulecreator.Interfaces;

import com.example.schedulecreator.Database.Worker;

public interface PersonnelCRUDInterface {
    void addWorker(Worker worker);
    void updateWorker(Worker worker);
    boolean deleteWorker(Worker worker);
}
