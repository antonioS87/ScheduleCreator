package com.example.schedulecreator.controllers;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.example.schedulecreator.DateUtils.IDateFormaterUtil;
import com.example.schedulecreator.dialogs.DatePickerDialogFragment;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StarEndDatePickersController {

    private TextView mPickStartDateTv;
    private TextView mPickEndDateTv;
    private MutableLiveData<Date> mStarDate;
    private MutableLiveData<Date> mEndDate;
    private IDateFormaterUtil mDateFormatterUtil;

    public StarEndDatePickersController(TextView pickStartDateTv,
                                        TextView pickEndDateTv,
                                        IDateFormaterUtil dateFormaterUtil,
                                        @NonNull MutableLiveData<Date> startDate,
                                        @NonNull MutableLiveData<Date> endDate,
                                        FragmentActivity activity){
        mPickStartDateTv = pickStartDateTv;
        mPickEndDateTv = pickEndDateTv;
        mDateFormatterUtil = dateFormaterUtil;
        mStarDate = startDate;
        mEndDate = endDate;
        initializeDatePickers( activity );

    }

    private void initializeDatePickers(final FragmentActivity activity ){

        //Prevent keyboard from appearing when edit text is clicked
        mPickStartDateTv.setInputType( InputType.TYPE_NULL );
        mPickEndDateTv.setInputType( InputType.TYPE_NULL );

        mStarDate.observe( activity , new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
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
        DialogFragment datePickerDialogFragment = new DatePickerDialogFragment( mStarDate, mEndDate, dateTag );
        datePickerDialogFragment.show( activity.getSupportFragmentManager(), null);
    }

    private void setEndDateTv(Date date) {
        mPickEndDateTv.setText( formatDateToString( date ) );
    }


    private void setStartTextTv(Date date) {
        mPickStartDateTv.setText( formatDateToString( date ) );
    }

    private String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
        return format.format( date );
    }

}
