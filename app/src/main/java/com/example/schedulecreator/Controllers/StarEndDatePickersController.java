package com.example.schedulecreator.Controllers;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.example.schedulecreator.DateUtils.IDateFormaterUtil;
import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.Dialogs.DatePickerDialogFragment;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StarEndDatePickersController {

    private TextView mPickStartDateTv;
    private TextView mPickEndDateTv;
    private LiveData<Date> mStarDate;
    private LiveData<Date> mEndDate;
    private IDateFormaterUtil mDateFormatterUtil;
    private SchedulerSettingsManager mSchedulerSettingsMng;

    public StarEndDatePickersController(TextView pickStartDateTv,
                                        TextView pickEndDateTv,
                                        IDateFormaterUtil dateFormaterUtil,
                                        SchedulerSettingsManager schedulerSettingsManager,
                                        FragmentActivity activity){
        mSchedulerSettingsMng = schedulerSettingsManager;
        mPickStartDateTv = pickStartDateTv;
        mPickEndDateTv = pickEndDateTv;
        mDateFormatterUtil = dateFormaterUtil;
        mStarDate = mSchedulerSettingsMng.getStartDate();
        mEndDate = mSchedulerSettingsMng.getEndDate();
        initializeDatePickers( activity );

    }

    private void initializeDatePickers(final FragmentActivity activity ){

        //Prevent keyboard from appearing when edit text is clicked
        mPickStartDateTv.setInputType( InputType.TYPE_NULL );
        mPickEndDateTv.setInputType( InputType.TYPE_NULL );

        mStarDate.observe( activity , new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                Log.d("antonio_test", " StartEndDatePickersController; start date changed: " + date.toString());
                setStartTextTv( date );
            }
        });

        mEndDate.observe(activity, new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                setEndDateTv( date );
            }
        });


        //Set OnClickListener and OnFocusChangeListener to capture all clicks
        mPickStartDateTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePickerDialog(activity, DatePickerDialogFragment.StartOrEndDate.STAR_DATE);


            }

        });

        mPickEndDateTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(activity, DatePickerDialogFragment.StartOrEndDate.END_DATE);
            }
        });

        mPickStartDateTv.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(view.isFocused()) {
                    showDatePickerDialog( activity, DatePickerDialogFragment.StartOrEndDate.STAR_DATE);
                }
            }
        });

        mPickEndDateTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                    showDatePickerDialog(activity, DatePickerDialogFragment.StartOrEndDate.END_DATE );
            }
        });
    }

    //Show the date picker for start date
    private void showDatePickerDialog( FragmentActivity activity, DatePickerDialogFragment.StartOrEndDate dateTag) {
        DialogFragment datePickerDialogFragment = new DatePickerDialogFragment( mSchedulerSettingsMng, dateTag );
        datePickerDialogFragment.show( activity.getSupportFragmentManager(), null);
    }

    private void setEndDateTv(Date date) {
        mPickEndDateTv.setText( formatDateToString( date ) );
    }


    private void setStartTextTv(Date date) {
        Log.d("antonio_test", " StartEndDatePickersController; mPickStartDateTv: " + mPickStartDateTv.getId() + " date: " + date.toString());
        mPickStartDateTv.setText( formatDateToString( date ) );
    }

    private String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
        return format.format( date );
    }

}
