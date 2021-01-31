package com.example.schedulecreator.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.Dialogs.GenerateScheduleDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScheduleCreatorFragment extends Fragment {

    private SchedulerSettingsManager mSchedulerSettingsMng;
    private FloatingActionButton mGenerateScheduleBtn;
    private DatesPickingFragment datesFragment = new DatesPickingFragment();
    private SchedulerCreatorPersonnelListFragment personnelListFragment = new SchedulerCreatorPersonnelListFragment();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View scheduleCreatorFragmentView = inflater.inflate(R.layout.schedule_creator_fragment, container, false);
        mGenerateScheduleBtn = scheduleCreatorFragmentView.findViewById(R.id.generate_schedule_button);

        mSchedulerSettingsMng = new ViewModelProvider(getActivity())
                .get(MainActivityViewModel.class);

        getChildFragmentManager().beginTransaction().
                add( R.id.main_container, datesFragment, "dates_fragment").
                add( R.id.main_container, personnelListFragment, "personnel_list_fragment").
                commitNow();

        mGenerateScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateScheduleDialog generateScheduleDialog = GenerateScheduleDialog.newInstance(mSchedulerSettingsMng);
                generateScheduleDialog.show(getFragmentManager(), "schedule_generator_dialog");
            }
        });

        return scheduleCreatorFragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();



    }
}
