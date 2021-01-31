package com.example.schedulecreator.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkerDbDao {
    @Query("SELECT * FROM workers")
    List<Worker> getAll();

    @Insert
    void insertWorker( Worker worker );

    @Insert
    void insertWorkers( List<Worker> workers );


    @Delete
    void deleteWorker( Worker worker );

    @Update
    void updateWorker( Worker worker );
}
