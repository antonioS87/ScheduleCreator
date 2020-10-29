package com.example.schedulecreator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.MainActivityViewModel;
import com.example.schedulecreator.R;

import java.util.Calendar;
import java.util.Date;

public class ScheduleCreatorFragment extends Fragment {

    private MainActivityViewModel mViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View scheduleCreatorFragmentView = inflater.inflate(R.layout.schedule_creator_fragment, container, false);


        return scheduleCreatorFragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        //DatesPickingFragment receives MutableLiveData<Date> objects from ViewModel and sets
        //their value. That way the ViewModel is notified of every date change
        DatesPickingFragment datesFragment = DatesPickingFragment.newInstance( mViewModel.getStartDate(), mViewModel.getEndDate() );
        PersonnelListFragment personnelListFragment = PersonnelListFragment.newInstance();

        getChildFragmentManager().beginTransaction().
                add( R.id.main_container, datesFragment, "dates_fragment").
                add( R.id.main_container, personnelListFragment, "personnel_list_fragment").
                commitNow();

    }
}
