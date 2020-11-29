package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;

public interface PersonnelCRUDInterface {
    void addWorker(Worker worker);
    void updateWorker(Worker worker);
    boolean deleteWorker(Worker worker);
}
