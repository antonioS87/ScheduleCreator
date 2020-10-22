package com.example.schedulecreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.util.Log;

import com.example.schedulecreator.fragments.DatesPickingFragment;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainContainer = findViewById( R.id.main_container );

        //DatesPickingFragment receives MutableLiveData<Date> objects from ViewModel and sets
        //their value. That way the ViewModel is notified of every date change
        DatesPickingFragment datesFragment = new DatesPickingFragment(new MutableLiveData<Date>(), new MutableLiveData<Date>());

        Calendar cal = Calendar.getInstance(); // that is NOW for the timezone configured on the computer.
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set( Calendar.YEAR, 2010);
        cal.set( Calendar.MONTH, 1);
        Date date = cal.getTime();


        getSupportFragmentManager().beginTransaction().replace( R.id.main_container, datesFragment, null).commitNow();
    }
}