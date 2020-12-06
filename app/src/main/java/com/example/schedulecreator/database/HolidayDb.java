package com.example.schedulecreator.database;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "holidays")
public class HolidayDb {

    @PrimaryKey( autoGenerate = true )
    @NonNull
    @ColumnInfo( name = "id")
    private Integer id;
    @ColumnInfo( name = "name" )
    private String name;
    @ColumnInfo(name = "day")
    private int day;
    @ColumnInfo(name="month")
    private int month;
    @ColumnInfo(name="year")
    private int year;

    public HolidayDb( String name, int day, int month, int year){
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }





}
