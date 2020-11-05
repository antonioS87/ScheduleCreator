package com.example.schedulecreator.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface WorkerDbDao {
    @Query("SELECT * FROM workers")
    Single<List<Worker>> getAll();

    @Insert
    void insertWorker( Worker worker );

    @Delete
    void deleteWorker( Worker worker );

    @Update
    void updateWorker( Worker worker );
}
