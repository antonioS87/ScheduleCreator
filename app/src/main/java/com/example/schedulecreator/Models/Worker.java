package com.example.schedulecreator.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class Worker implements Comparable {

    private String mFirstName;
    private String mLastName;
    private String mId;
    private MutableLiveData<Boolean> mSelected = new MutableLiveData<>();
    private int mColor;

    public Worker( String lastName, String firstName, String id, Boolean selected ){
        Log.d("test_tag_antonio", " Worker constructor; first name: " + firstName + " last name: " + lastName + " id: " + id + " selected: " + selected);
        this.mLastName = lastName;
        this.mFirstName = firstName;
        this.mId = id;
        this.mSelected.setValue( selected );
    }

    public void setColor(int color ){
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public String getLastName(){
        return this.mLastName;
    }

    public MutableLiveData<Boolean> getSelected() {
        return mSelected;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    @Override
    public int compareTo(Object o) {

        return this.mLastName.compareTo( ((Worker)o).mLastName );
    }
}
