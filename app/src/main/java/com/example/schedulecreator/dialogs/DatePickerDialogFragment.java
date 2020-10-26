package com.example.schedulecreator.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.schedulecreator.Models.Day;

import java.util.Date;

public  class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private final MutableLiveData<Date> starDate;
    private final MutableLiveData<Date> endDate;
    private MutableLiveData<Date> pickedDate;
    private StartOrEndDate mDateTag;

    public DatePickerDialogFragment( MutableLiveData<Date> startDate, MutableLiveData<Date> endDate, StartOrEndDate dateTag ){
        this.starDate = startDate;
        this.endDate = endDate;
        this.mDateTag = dateTag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Date limitDate = null;
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        if(mDateTag == StartOrEndDate.STAR_DATE){
            pickedDate = starDate;
            limitDate = endDate.getValue();
            if( limitDate != null ){
                dialog.getDatePicker().setMaxDate( limitDate.getTime() );
            }
        }else if( mDateTag == StartOrEndDate.END_DATE){
            pickedDate = endDate;
            limitDate = starDate.getValue();
            if( limitDate != null ){
                dialog.getDatePicker().setMinDate( limitDate.getTime() );
            }
        }

        if( this.pickedDate.getValue() != null ){
            c.setTime( this.pickedDate.getValue() );
        }




//        if( mDateTag == StartOrEndDate.END_DATE ){
//            dialog.getDatePicker().setMaxDate( pickedDate.getValue().getTime() );
//        }else if( mDateTag == StartOrEndDate.STAR_DATE ){
//            dialog.getDatePicker().setMinDate( pickedDate.getValue().getTime() );
//        }


        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.YEAR, year );
        calendar.set( Calendar.MONTH, month );
        calendar.set( calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        this.pickedDate.setValue( date );

        // Do something with the date chosen by the user
    }

    public enum StartOrEndDate {
        STAR_DATE,
        END_DATE
    }
}
