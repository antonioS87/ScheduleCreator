package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.Database.Worker;

import java.util.ArrayList;

public interface PersonnelManager extends PersonnelCRUDInterface{
    LiveData<ArrayList<Worker>> getObservableWorkersList();
}
