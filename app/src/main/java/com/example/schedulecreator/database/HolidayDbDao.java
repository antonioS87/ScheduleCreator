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
public interface HolidayDbDao {
    @Query("SELECT * FROM holidays")
    List<HolidayDb> getAll();

    @Insert
    void insertHoliday( HolidayDb holiday );

    @Insert
    void insertHolidays( List<HolidayDb> holidays);

    @Delete
    void deleteHoliday( HolidayDb holiday );

    @Update
    void updateHoliday( HolidayDb holiday );

    @Query("DELETE FROM holidays")
    void deleteAll();

}
