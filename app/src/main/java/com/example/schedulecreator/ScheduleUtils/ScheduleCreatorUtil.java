package com.example.schedulecreator.ScheduleUtils;

import android.util.Log;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.Models.DayOfWeek;
import com.example.schedulecreator.Models.Schedule;
import com.example.schedulecreator.Models.ScheduleGeneratorSettings;
import com.example.schedulecreator.database.Worker;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ScheduleCreatorUtil {

    //List of worker available for scheduling
    private ArrayList<Worker> mWorkersList;

    //Schedule in which ordered scheduled days will be stored
    private Schedule mSchedule;
    private Date mStartDate;
    private Date mEndDate;
    private DayOfWeek dayOfWeek;

    public void generateSchedule(SchedulerSettingsManager settings){
        mStartDate = settings.getStartDate().getValue();
        mEndDate = settings.getEndDate().getValue();
        mWorkersList = settings.getSchedulerSettPersonnelList().getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mStartDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while(calendar.getTime().compareTo(mEndDate) != 1){

            //Sunday
            if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
                Log.d("generator_test", " day: " + dateFormat.format(calendar.getTime()) + " " + DayOfWeek.SUNDAY);
            //Ordinary
            }else if(calendar.get(Calendar.DAY_OF_WEEK) > 1 && calendar.get(Calendar.DAY_OF_WEEK) < 5 ){
                Log.d("generator_test", " day: " + dateFormat.format(calendar.getTime()) + " " + DayOfWeek.ORDINARY);
            //Thursday
            }else if(calendar.get(Calendar.DAY_OF_WEEK) == 5){
                Log.d("generator_test", " day: " + dateFormat.format(calendar.getTime()) + " " + DayOfWeek.THURSDAY);
            //Friday
            }else if(calendar.get(Calendar.DAY_OF_WEEK) == 6){
                Log.d("generator_test", " day: " + dateFormat.format(calendar.getTime()) + " " + DayOfWeek.FRIDAY);
            //Saturday
            }else if(calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                Log.d("generator_test", " day: " + dateFormat.format(calendar.getTime()) + " " + DayOfWeek.SATURDAY);
            }

            calendar.add(Calendar.DATE, 1);


        }


    }

}
