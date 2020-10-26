package com.example.schedulecreator.fragments;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.schedulecreator.DateUtils.DateFormater;
import com.example.schedulecreator.R;
import com.example.schedulecreator.controllers.StarEndDatePickersController;
import com.example.schedulecreator.dialogs.DatePickerDialogFragment;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesPickingFragment extends Fragment {

    private TextView mPickStartDateTv;
    private TextView mPickEndDateTv;
    private MutableLiveData<Date> mStartDate;
    private MutableLiveData<Date> mEndDate;
    private StarEndDatePickersController mdatePickersController;

    public DatesPickingFragment(MutableLiveData<Date> startDate, MutableLiveData<Date> endDate) {
        this.mStartDate = startDate;
        this.mEndDate = endDate;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.dates_picking_fragment_layout, container, false );
        mPickStartDateTv = view.findViewById( R.id.picked_start_date_tv );
        mPickEndDateTv = view.findViewById( R.id.picked_end_date_tv );

        mdatePickersController = new StarEndDatePickersController(
                mPickStartDateTv,
                mPickEndDateTv,
                new DateFormater(),
                mStartDate,
                mEndDate,
                getActivity());


        return view;
    }

    private String getInitialEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH,1 );
        Date date = calendar.getTime();

        return formatDateToString( date );
    }

    private String getInitialStartDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        return formatDateToString( date );
    }


    private String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / YYYY");
        return format.format( date );
    }

    private void setEndDateTv(Date date) {

        Log.d("date_tag", " setEndDateTv");
        mPickEndDateTv.setText( formatDateToString( date ) );
    }


    private void setStartTextTv(Date date) {
        Log.d("date_tag", " setStartTextTv");
        mPickStartDateTv.setText( formatDateToString( date ) );
    }

    //Show the date picker for start date
    private void showDatePickerDialog(MutableLiveData<Date> startDate, MutableLiveData<Date> endDate, DatePickerDialogFragment.StartOrEndDate dateTag) {
        DialogFragment datePickerDialogFragment = new DatePickerDialogFragment( startDate, endDate, dateTag );
        datePickerDialogFragment.show( getFragmentManager(), null);
    }

    private void initializeDatePickers(View view ){

        mPickStartDateTv = view.findViewById( R.id.picked_start_date_tv );
        mPickEndDateTv = view.findViewById( R.id.picked_end_date_tv );

        //Prevent keyboard from appearing when edit text is clicked
        mPickStartDateTv.setInputType( InputType.TYPE_NULL );
        mPickEndDateTv.setInputType( InputType.TYPE_NULL );

        //Set initial values
        mPickStartDateTv.setText( getInitialStartDate() );

        //Set OnClickListener and OnFocusChangeListener to capture all clicks
        mPickStartDateTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MutableLiveData<Date> startDate = new MutableLiveData<>();
                mStartDate.observe(DatesPickingFragment.this, new Observer<Date>() {
                    @Override
                    public void onChanged(Date date) {
                        Log.d("date_tag", " mPickStartDateTv date changed");
                        setStartTextTv( date );
                    }
                });

                showDatePickerDialog(mStartDate, mEndDate, DatePickerDialogFragment.StartOrEndDate.STAR_DATE);


            }

        });

        mPickEndDateTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MutableLiveData<Datate> endDate = new MutableLiveData<>();
                mEndDate.observe(DatesPickingFragment.this, new Observer<Date>() {
                    @Override
                    public void onChanged(Date date) {
                        setEndDateTv( date );
                    }
                });

                showDatePickerDialog(mEndDate, mEndDate, DatePickerDialogFragment.StartOrEndDate.END_DATE);
            }
        });

        mPickStartDateTv.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(view.isFocused()) {
//                    MutableLiveData<Date> date = new MutableLiveData<>();
                    mStartDate.observe(DatesPickingFragment.this, new Observer<Date>() {
                        @Override
                        public void onChanged(Date date) {

                            setStartTextTv(date);

                        }
                    });
                    showDatePickerDialog(mStartDate, mEndDate, DatePickerDialogFragment.StartOrEndDate.STAR_DATE);
                }
            }
        });

        mPickEndDateTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                //Show dialog only if view is focused
                if(view.isFocused()) {
//                    MutableLiveData<Date> date = new MutableLiveData<>();
                    mEndDate.observe(DatesPickingFragment.this, new Observer<Date>() {
                        @Override
                        public void onChanged(Date date) {
                            setEndDateTv( date );

                        }
                    });
                    showDatePickerDialog(mEndDate, mEndDate, DatePickerDialogFragment.StartOrEndDate.END_DATE);
                }
            }
        });

    }



}
