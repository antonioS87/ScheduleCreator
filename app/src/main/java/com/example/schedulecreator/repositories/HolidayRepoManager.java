package com.example.schedulecreator.repositories;

import androidx.lifecycle.LiveData;

import com.example.schedulecreator.holidayApi.Holiday;

import java.util.ArrayList;
import java.util.List;

public interface HolidayRepoManager {

    LiveData<ArrayList<Holiday>> getHolidays();

}
