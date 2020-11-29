package com.example.schedulecreator.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.Interfaces.PersonnelRepositoryListener;
import com.example.schedulecreator.MainActivity;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.dialogs.DialogsHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PersonnelManagementFragment extends Fragment {

    private FloatingActionButton mAddWorkerButton;
    private PersonnelRepoManager mPersonnelManager;
    private RecyclerView mPersonnelRV;
    private PersonnelManagerRecyclerAdapter mPersonnelRvAdapter;
    private PersonnelManager personnelManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.personnel_management_fragment_layout, container, false);
        mAddWorkerButton = view.findViewById(R.id.add_worker_button);
        mPersonnelManager = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        mPersonnelRV = view.findViewById(R.id.personnel_edit_recycler_view);
        mPersonnelRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mPersonnelRvAdapter = new PersonnelManagerRecyclerAdapter( mPersonnelManager.getObservableWorkersList().getValue() );
        mPersonnelRV.setAdapter(mPersonnelRvAdapter);

        mPersonnelManager.getObservableWorkersList().observe(getActivity(), new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                mPersonnelRvAdapter.setWorkerList( workers, getFragmentManager());
                mPersonnelRvAdapter.notifyDataSetChanged();
            }
        });

        mAddWorkerButton.setOnClickListener( view1 -> {
            DialogFragment addworkerDialog = DialogsHandler.getAddWorkerDialog();
            addworkerDialog.show(getFragmentManager(), "add_worker_dialog");
        });

        return view;
    }


}
