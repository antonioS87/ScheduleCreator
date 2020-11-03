package com.example.schedulecreator.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface WorkerDbDao {
    @Query("SELECT * FROM workers")
    List<WorkerDb> getAll();

    @Insert
    void insertWorker( WorkerDb worker );

    @Delete
    void deleteWorker( WorkerDb worker );

    @Update
    void updateWorker( WorkerDb worker );
}
