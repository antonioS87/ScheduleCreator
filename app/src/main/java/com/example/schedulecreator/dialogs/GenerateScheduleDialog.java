package com.example.schedulecreator.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.R;
import com.example.schedulecreator.ScheduleUtils.ScheduleCreatorUtil;
import com.example.schedulecreator.holidayApi.Holiday;
import com.example.schedulecreator.holidayApi.HolidayApiClient;
import com.example.schedulecreator.holidayApi.HolidayApiInterface;
import com.example.schedulecreator.holidayApi.HolidayResponse;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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


        mGenerateScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduleCreatorUtil schUtil = new ScheduleCreatorUtil();
                schUtil.generateSchedule(mScheduleSettings);
                HolidayApiInterface holidayInterface = HolidayApiClient.getClient().create(HolidayApiInterface.class);
                String apiKey = "82cde7fe8dab289874ce616896f8bcb6c4b9cda7";
                Call<HolidayResponse> call = holidayInterface.getHolidays(apiKey, "hr", "2021");

                call.enqueue(new Callback<HolidayResponse>() {
                    @Override
                    public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                        ArrayList<Holiday> holidays = new ArrayList<>(response.body().getResponse().getHolidays());
                        for (Holiday holiday : holidays){
                            Log.d("holidays","holiday: " + holiday.getName() + " date: " + holiday.getDate().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<HolidayResponse> call, Throwable t) {

                    }
                });
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
