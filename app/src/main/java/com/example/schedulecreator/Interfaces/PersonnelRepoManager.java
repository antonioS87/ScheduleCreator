package com.example.schedulecreator.Interfaces;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public interface PersonnelRepoManager extends PersonnelCRUDInterface  {
    void addPersonnelRepositoryListener(PersonnelRepositoryListener personnelRepositoryListener);
    void requestWorkerListRefresh();

}
