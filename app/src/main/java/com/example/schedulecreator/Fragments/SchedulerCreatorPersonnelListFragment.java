package com.example.schedulecreator.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.schedulecreator.Interfaces.SchedulerSettingsManager;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.Database.Worker;

import java.util.ArrayList;

public class SchedulerCreatorPersonnelListFragment extends Fragment {

    private RecyclerView mPersonnelRecycleView;
    private SchedulerSettingsManager mSchedulerSettingsMng;
    private LiveData<ArrayList<Worker>> mPersonnelList;
    private StaggeredGridLayoutManager mLayoutManager;
    private PersonnelRecyclerAdapter mRecyclerAdapter;

    public static SchedulerCreatorPersonnelListFragment newInstance(MutableLiveData<ArrayList<Worker>> personnelList) {
        SchedulerCreatorPersonnelListFragment personnelListFragment = new SchedulerCreatorPersonnelListFragment();
        personnelListFragment.setPersonnelList( personnelList );
        return personnelListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.personnel_list_fragment_layout, container, false);

        mSchedulerSettingsMng = new ViewModelProvider( getActivity())
                .get( MainActivityViewModel.class );
        mPersonnelList = mSchedulerSettingsMng.getSchedulerSettPersonnelList();

//        Log.d("test_tag_antonio", " PersonnelListFragment; onCreateView; mPersonnelList size: " + mPersonnelList.getValue().size());
        mPersonnelRecycleView = view.findViewById( R.id.personnel_recycler_view );
        mLayoutManager = new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL);
        mPersonnelRecycleView.setLayoutManager( mLayoutManager );
        mRecyclerAdapter = new PersonnelRecyclerAdapter( new ArrayList<Worker>() );
        mPersonnelRecycleView.setAdapter( mRecyclerAdapter );

        //Setting
        mPersonnelList.observe(this, new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                Log.d("test_tag_antonio", " PersonnelListFragment; onCreateView; workersLis size: " + workers.size());
                if( mRecyclerAdapter == null ){
                    mRecyclerAdapter = new PersonnelRecyclerAdapter( workers );
                    mPersonnelRecycleView.setAdapter( mRecyclerAdapter);
                    mRecyclerAdapter.notifyDataSetChanged();
                }else{
                    mRecyclerAdapter.setWorkerList( workers );
                    mRecyclerAdapter.notifyDataSetChanged();
                }

            }
        });

        return view;
    }

    public void setPersonnelList( MutableLiveData<ArrayList<Worker>> personnelList ){
        this.mPersonnelList = personnelList;
    }


}
