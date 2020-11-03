package com.example.schedulecreator.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class Worker implements Comparable {

    private String firstName;
    private String lastName;
    private String id;
    private MutableLiveData<Boolean> selected = new MutableLiveData<>();
    private int mColor;

    public Worker( String lastName, String firstName, String id, Boolean selected ){
        Log.d("test_tag_antonio", " Worker constructor; first name: " + firstName + " last name: " + lastName + " id: " + id + " selected: " + selected);
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.selected.setValue( selected );
    }

    public void addDay( Day day ){

    }

    public void setColor(int color ){
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public String getLastName(){
        return this.lastName;
    }

    public MutableLiveData<Boolean> getSelected() {
        return selected;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {

        return this.lastName.compareTo( ((Worker)o).lastName );
    }
}
