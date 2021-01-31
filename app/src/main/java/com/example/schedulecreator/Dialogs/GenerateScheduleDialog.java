package com.example.schedulecreator.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.R;
import com.example.schedulecreator.ScheduleUtils.ScheduleCreatorUtil;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.Repositories.HolidayRepoManager;
// ...

public class GenerateScheduleDialog extends DialogFragment {

    private SchedulerSettingsManager mScheduleSettings;
    private Button mGenerateScheduleBtn;
    private Button mCancelBtn;

    public GenerateScheduleDialog() {

    }

    public static GenerateScheduleDialog newInstance(SchedulerSettingsManager scheduleSettingsManager) {
        GenerateScheduleDialog generateScheduleDialog = new GenerateScheduleDialog();
        generateScheduleDialog.setSettings(scheduleSettingsManager);
        return generateScheduleDialog;
    }

    private void setSettings(SchedulerSettingsManager settings) {
        mScheduleSettings = settings;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generate_schedule_dialog_layout, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGenerateScheduleBtn = view.findViewById(R.id.generate_schedule_dialog_button);
        mCancelBtn = view.findViewById(R.id.cancel_schedule_generation_button);

        HolidayRepoManager holidayRepoManager = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        mGenerateScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduleCreatorUtil schUtil = new ScheduleCreatorUtil();
                schUtil.generateSchedule(mScheduleSettings, getActivity(), holidayRepoManager);

            }
        });


        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateScheduleDialog.this.dismiss();
            }
        });
    }
}
