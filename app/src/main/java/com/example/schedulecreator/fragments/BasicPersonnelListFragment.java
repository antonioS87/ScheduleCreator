package com.example.schedulecreator.fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.schedulecreator.ViewModels.PersonnelListViewModel;
import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;

public class BasicPersonnelListFragment extends Fragment {

    private RecyclerView mPersonnelRecycleView;
    private PersonnelListViewModel mViewModel;
    private MutableLiveData<ArrayList<Worker>> mPersonnelList;
    private StaggeredGridLayoutManager mLayoutManager;
    private PersonnelRecyclerAdapter mRecyclerAdapter;


}
