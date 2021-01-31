package com.example.schedulecreator.Repositories;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.Database.HolidayDb;

import java.util.ArrayList;

public interface HolidayRepoManager {

    LiveData<ArrayList<HolidayDb>> getHolidays();


}
