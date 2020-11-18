package com.example.schedulecreator.database;
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

    public Worker( String mFirstName, String mLastName){
//        Log.d("test_tag_antonio", " Worker constructor; first name: " + firstName + " last name: " + lastName + " id: " + id + " selected: " + selected);
        this.mLastName = mLastName;
        this.mFirstName = mFirstName;
    }

    public int getId() {
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
}
