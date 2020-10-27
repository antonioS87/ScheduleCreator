package com.example.schedulecreator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.MainActivityViewModel;
import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.R;

import java.util.ArrayList;

public class PersonnelListFragment  extends Fragment {

    private RecyclerView mPersonnelRecycleView;
    private MainActivityViewModel mViewModel;
    private MutableLiveData<ArrayList<Worker>> mPersonnelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.personnel_list_fragment_layout, container, false);

        mViewModel = new ViewModelProvider(this).get( MainActivityViewModel.class );
        mPersonnelRecycleView = view.findViewById( R.id.personnel_recycler_view );

        mPersonnelList = mViewModel.getPersonnelList();


        return view;
    }
}
