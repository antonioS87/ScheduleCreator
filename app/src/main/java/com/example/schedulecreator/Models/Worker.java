package com.example.schedulecreator.Models;

import java.util.ArrayList;

public class Worker {

    private String name;
    private String id;
    private ArrayList<Day> sundays = new ArrayList<>();
    private ArrayList<Day> saturdays = new ArrayList<>();
    private ArrayList<Day> fridays = new ArrayList<>();
    private ArrayList<Day> weekdays = new ArrayList<>();
    private Boolean hadShiftTwoDaysAgo = false;
    private Boolean hadShiftThisWeek = false;

    public Worker( String name, String id ){
        this.name = name;
        this.id = id;
    }

    public void addDay( Day day ){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Day> getSundays() {
        return sundays;
    }

    public void setSundays(ArrayList<Day> sundays) {
        this.sundays = sundays;
    }

    public ArrayList<Day> getSaturdays() {
        return saturdays;
    }

    public void setSaturdays(ArrayList<Day> saturdays) {
        this.saturdays = saturdays;
    }

    public ArrayList<Day> getFridays() {
        return fridays;
    }

    public void setFridays(ArrayList<Day> fridays) {
        this.fridays = fridays;
    }

    public ArrayList<Day> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(ArrayList<Day> weekdays) {
        this.weekdays = weekdays;
    }

    public Boolean getHadShiftTwoDaysAgo() {
        return hadShiftTwoDaysAgo;
    }

    public void setHadShiftTwoDaysAgo(Boolean hadShiftTwoDaysAgo) {
        this.hadShiftTwoDaysAgo = hadShiftTwoDaysAgo;
    }

    public Boolean getHadShiftThisWeek() {
        return hadShiftThisWeek;
    }

    public void setHadShiftThisWeek(Boolean hadShiftThisWeek) {
        this.hadShiftThisWeek = hadShiftThisWeek;
    }
}
