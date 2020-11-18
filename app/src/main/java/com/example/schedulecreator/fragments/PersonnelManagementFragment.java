package com.example.schedulecreator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.MainActivityViewModel;
import com.example.schedulecreator.R;
import com.example.schedulecreator.dialogs.DialogsHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PersonnelManagementFragment extends Fragment {

    private FloatingActionButton mAddWorkerButton;
    private PersonnelManager mPersonnelManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.personnel_management_fragment_layout, container, false);
        mAddWorkerButton = view.findViewById(R.id.add_worker_button);
        mPersonnelManager = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        mAddWorkerButton.setOnClickListener( view1 -> {
            DialogsHandler.getAddWorkerDialog("Neki naslov");
        });

        return view;
    }
}
