package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;

public interface PersonnelManager extends PersonnelCRUDInterface{
    void getObservableWorkersList(LiveData<ArrayList<Worker>> workersList);
}