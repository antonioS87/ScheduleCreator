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
import com.example.schedulecreator.fragments.ScheduleCreatorFragment;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        ScheduleCreatorFragment scheduleCreatorFragment = new ScheduleCreatorFragment();

        getSupportFragmentManager().beginTransaction().
                add( R.id.activity_main_container, scheduleCreatorFragment, "schedule_creator_fragment").

                commitNow();

    }
}