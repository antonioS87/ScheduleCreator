package com.example.schedulecreator.ScheduleUtils;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.Models.DayOfWeek;
import com.example.schedulecreator.Models.Schedule;
import com.example.schedulecreator.Database.HolidayDb;
import com.example.schedulecreator.Database.Worker;
import com.example.schedulecreator.Repositories.HolidayRepoManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleCreatorUtil {

    //List of worker available for scheduling
    private ArrayList<Worker> selectedWorkers = new ArrayList<>();

    //Schedule in which ordered scheduled days will be stored
    private Schedule mSchedule;
    private Date mStartDate;
    private Date mEndDate;
    private DayOfWeek dayOfWeek;
    private ArrayList<HolidayDb> holidays;

    public void generateSchedule(SchedulerSettingsManager settings, LifecycleOwner activity, HolidayRepoManager holidayRepoManager){
        Log.d("generator_test", " Generating schedule....");
        mStartDate = settings.getStartDate().getValue();
        mEndDate = settings.getEndDate().getValue();

        if(holidayRepoManager.getHolidays().getValue() != null){
            holidays = holidayRepoManager.getHolidays().getValue();
        }

        //Create new schedule and fill it with blank dates
        Schedule schedule = new Schedule();
        Calendar cal = Calendar.getInstance();
        cal.setTime(mStartDate);
        while(cal.getTime().before(mEndDate)){

            //Get the date
            Date date = cal.getTime();

            //If the date is not a holiday assign it to an eligible worker
            if(!isHolliday(date)){
                Log.d("holiday_test", " day is NOT HOLIDAY: " + date.toString());
//                schedule.addDay(new ScheduledDay(date, getEligibleWorkerId(date)));
            }else{
                Log.d("holiday_test", " day IS HOLIDAY: " + date.toString());
//                schedule.addDay(new ScheduledDay(date, null));
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        for(Worker worker : settings.getSchedulerSettPersonnelList().getValue()){
            if(worker.getSelected().getValue()){
                selectedWorkers.add(worker);
                Log.d("antonio_test", " adding worker to selected list: " + worker.toString());
            }
        }


        holidayRepoManager.getHolidays().observe(activity, new Observer<ArrayList<HolidayDb>>() {
            @Override
            public void onChanged(ArrayList<HolidayDb> holidays) {
                for(HolidayDb holiday : holidays){
                    Log.d("generator_test", " Holiday: " + holiday.getName()  + " " + holiday.getDate());
                }
            }
        });


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mStartDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");




    }

    private String getEligibleWorkerId(Date date) {
        return "workerId";
    }

    private boolean isHolliday(Date date) {
        return holidays.contains(date) ? true : false;
    }

}
