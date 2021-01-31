package com.example.schedulecreator.Database;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "workers")
public class Worker  implements Comparable<Worker>{

    @PrimaryKey( autoGenerate = true )
    @NonNull
    @ColumnInfo( name = "id")
    private Integer mId;
    @ColumnInfo( name = "firstName" )
    private String mFirstName;
    @ColumnInfo(name = "lastName")
    private String mLastName;
    @ColumnInfo(name="color")
    private int mColor;

    @Ignore
    private MutableLiveData<Boolean> mSelected = new MutableLiveData<>();

    //Following variables will be used in ranking of the workers
    //Initially this info is not stored in database and is initialized to zero
    //In the future the app will be fixed to store this data based on implemented calendar
    @Ignore
    private int nOrdinaryDays = 0;
    @Ignore
    private int nThursdays = 0;
    @Ignore
    private int nFridays = 0;
    @Ignore
    private int nSaturdays = 0;
    @Ignore
    private int nSundays = 0;

    public int getnOrdinaryDays() {
        return nOrdinaryDays;
    }

    public void setnOrdinaryDays(int nOrdinaryDays) {
        this.nOrdinaryDays = nOrdinaryDays;
    }

    public int getnThursdays() {
        return nThursdays;
    }

    public void setnThursdays(int nThursdays) {
        this.nThursdays = nThursdays;
    }

    public int getnFridays() {
        return nFridays;
    }

    public void setnFridays(int nFridays) {
        this.nFridays = nFridays;
    }

    public int getnSaturdays() {
        return nSaturdays;
    }

    public void setnSaturdays(int nSaturdays) {
        this.nSaturdays = nSaturdays;
    }

    public int getnSundays() {
        return nSundays;
    }

    public void setnSundays(int nSundays) {
        this.nSundays = nSundays;
    }

    public Worker( String mFirstName, String mLastName){
//        Log.d("test_tag_antonio", " Worker constructor; first name: " + firstName + " last name: " + lastName + " id: " + id + " selected: " + selected);
        this.mLastName = mLastName;
        this.mFirstName = mFirstName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    @Override
    public int compareTo(Worker worker) {

        return this.mLastName.compareTo( worker.getLastName() );
    }

    public MutableLiveData<Boolean> getSelected() {
        return mSelected;
    }

    public void setSelected( MutableLiveData<Boolean> selected){
        mSelected = selected;
    }

    public void printNumOfDays(){
        Log.d("worker_days", "worker \n" + this.getFirstName() + " " + this.getLastName() + "\n"
                                        + "ordinary:  " + getnOrdinaryDays() + "\n"
                                        + "thursdays: " + getnThursdays() + "\n"
                                        + "fridays:   " + getnFridays() + "\n"
                                        + "saturdays: " + getnSaturdays() + "\n"
                                        + "sundays:   " + getnSundays() +  "\n") ;
    }

    @NonNull
    @Override
    public String toString() {
        return getLastName() + " " + getFirstName();
    }
}
