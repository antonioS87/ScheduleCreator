package com.example.schedulecreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.schedulecreator.fragments.DatesPickingFragment;
import com.example.schedulecreator.fragments.PersonnelListFragment;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mMainContainer;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mMainContainer = findViewById( R.id.main_container );


        //DatesPickingFragment receives MutableLiveData<Date> objects from ViewModel and sets
        //their value. That way the ViewModel is notified of every date change
        DatesPickingFragment datesFragment = DatesPickingFragment.newInstance( mViewModel.getStartDate(), mViewModel.getEndDate() );
//        DatesPickingFragment personnelListFragment = DatesPickingFragment.newInstance( mViewModel.getStartDate(), mViewModel.getEndDate() );
        PersonnelListFragment personnelListFragment = PersonnelListFragment.newInstance();

        Calendar cal = Calendar.getInstance(); // that is NOW for the timezone configured on the computer.
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set( Calendar.YEAR, 2010);
        cal.set( Calendar.MONTH, 1);
        Date date = cal.getTime();

        mViewModel.getStartDate().observe(this, new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                Log.d("test_tag_antonio", " MainActivity start date changed: " + date.toString());
            }
        });

        mViewModel.getEndDate().observe(this, new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                Log.d("test_tag_antonio", " MainActivity end date changed: " + date.toString());
            }
        });

        getSupportFragmentManager().beginTransaction().
                add( R.id.main_container, datesFragment, "dates_fragment").
                add( R.id.main_container, personnelListFragment, "personel_list_fragment").
                commitNow();

    }
}