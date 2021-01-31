package com.example.schedulecreator.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.DateUtils.DateFormater;
import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.Controllers.StarEndDatePickersController;
import java.util.Date;

public class DatesPickingFragment extends Fragment {

    private TextView mPickStartDateTv;
    private TextView mPickEndDateTv;
    private LiveData<Date> mStartDate;
    private LiveData<Date> mEndDate;
    private StarEndDatePickersController mdatePickersController;
    private SchedulerSettingsManager mSchedulerSettingsMng;

    public DatesPickingFragment(){

    }

//    public static DatesPickingFragment newInstance(MutableLiveData<Date> startDate, MutableLiveData<Date> endDate) {
//        DatesPickingFragment datesPickingFragment = new DatesPickingFragment();
//        datesPickingFragment.setArguments( startDate, endDate );
//        return datesPickingFragment;
//    }

//    private void setArguments(MutableLiveData<Date> startDate, MutableLiveData<Date> endDate) {
//        this.mStartDate = startDate;
//        this.mEndDate = endDate;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.dates_picking_fragment_layout, container, false );

        mPickStartDateTv = view.findViewById( R.id.picked_start_date_tv );
        mPickEndDateTv = view.findViewById( R.id.picked_end_date_tv );

        return view;
    }

    @Override
    public void onResume() {

        mSchedulerSettingsMng = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mdatePickersController = new StarEndDatePickersController(
                mPickStartDateTv,
                mPickEndDateTv,
                new DateFormater(),
                mSchedulerSettingsMng,
                getActivity() );


        super.onResume();
    }
}
