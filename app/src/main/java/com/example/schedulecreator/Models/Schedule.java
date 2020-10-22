package com.example.schedulecreator.Models;

import java.util.ArrayList;

//Shedule is used to store ordered list of scheduled days
public class Schedule {

    private ArrayList<Day> schedule;

    public Schedule(){
        this.schedule = new ArrayList<>();
    }

    public void addDay( Day day ){
        this.schedule.add( day );
    }

    public ArrayList<Day> getSchedule() {
        return schedule;
    }

}
