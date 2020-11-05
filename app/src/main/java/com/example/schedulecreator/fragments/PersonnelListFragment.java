package com.example.schedulecreator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.schedulecreator.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;

public class PersonnelListFragment  extends Fragment {

    private RecyclerView mPersonnelRecycleView;
    private MainActivityViewModel mViewModel;
    private MutableLiveData<ArrayList<Worker>> mPersonnelList;
    private StaggeredGridLayoutManager mLayoutManager;
    private PersonnelRecyclerAdapter mRecyclerAdapter;

    public static PersonnelListFragment newInstance( MutableLiveData<ArrayList<Worker>> personnelList) {
        PersonnelListFragment personnelListFragment = new PersonnelListFragment();
        personnelListFragment.setPersonnelList( personnelList );
        return personnelListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.personnel_list_fragment_layout, container, false);

        mViewModel = new ViewModelProvider( getActivity() ).get( MainActivityViewModel.class );
        mPersonnelList = mViewModel.getScheduleGeneratorSettings().getPersonnelList();

        Log.d("test_tag_antonio", " PersonnelListFragment; onCreateView; mPersonnelList size: " + mPersonnelList.getValue().size());
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
