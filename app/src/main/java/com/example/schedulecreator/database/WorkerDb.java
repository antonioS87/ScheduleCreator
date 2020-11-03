package com.example.schedulecreator.database;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workers")
public class WorkerDb  {

    @PrimaryKey
    @NonNull
    @ColumnInfo( name = "id" )
    private String mId;
    @ColumnInfo( name = "firsName" )
    private String mFirstName;
    @ColumnInfo(name = "lastName")
    private String mLastName;
    @ColumnInfo(name="color")
    private int mColor;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }





}
