package com.example.schedulecreator.dialogs;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import java.util.Date;

public  class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private final MutableLiveData<Date> mStarDate;
    private final MutableLiveData<Date> mEndDate;
    private MutableLiveData<Date> mPickedDate;
    private StartOrEndDate mDateTag;

    public DatePickerDialogFragment( MutableLiveData<Date> startDate, MutableLiveData<Date> endDate, StartOrEndDate dateTag ){
        this.mStarDate = startDate;
        this.mEndDate = endDate;
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
            mPickedDate = mStarDate;
            limitDate = mEndDate.getValue();
            if( limitDate != null ){
                dialog.getDatePicker().setMaxDate( limitDate.getTime() );
            }
        }else if( mDateTag == StartOrEndDate.END_DATE){
            mPickedDate = mEndDate;
            limitDate = mStarDate.getValue();
            if( limitDate != null ){
                dialog.getDatePicker().setMinDate( limitDate.getTime() );
            }
        }



        if( this.mPickedDate.getValue() != null ){
            c.setTime( this.mPickedDate.getValue() );
            int startYear = c.get(Calendar.YEAR);
            int startMonth = c.get(Calendar.MONTH);
            int startDay = c.get(Calendar.DAY_OF_MONTH);
            dialog.getDatePicker().updateDate( startYear, startMonth, startDay);
        }

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.YEAR, year );
        calendar.set( Calendar.MONTH, month );
        calendar.set( calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        this.mPickedDate.setValue( date );

    }

    public enum StartOrEndDate {
        STAR_DATE,
        END_DATE
    }
}
