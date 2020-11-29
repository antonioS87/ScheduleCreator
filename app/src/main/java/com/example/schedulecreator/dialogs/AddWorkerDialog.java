package com.example.schedulecreator.dialogs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.DialogFragment;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.R;
import com.example.schedulecreator.database.Worker;
import com.google.android.material.snackbar.Snackbar;
// ...

public class AddWorkerDialog extends DialogFragment {

    private EditText mFirstNameET;
    private EditText mLastNameET;
    private Button mAddWorkerButton;
    private Button mCancelAddWorkerButton;
    private PersonnelManager mPersonnelManager;

    public AddWorkerDialog(PersonnelManager personnelManager) {
        mPersonnelManager = personnelManager;
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static AddWorkerDialog newInstance(PersonnelManager personnelManager) {
        AddWorkerDialog addWorkerDialog = new AddWorkerDialog(personnelManager);
        return addWorkerDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_worker_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mFirstNameET = view.findViewById(R.id.new_worker_firstName_et);
        mLastNameET = view.findViewById(R.id.new_worker_lastName_et);
        mAddWorkerButton = view.findViewById(R.id.add_new_worker_button);
        mCancelAddWorkerButton = view.findViewById(R.id.cancel_add_worker_button);

        // Show soft keyboard automatically and request focus to field
        mFirstNameET.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mAddWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mFirstNameET.getText().toString();
                String lastName = mLastNameET.getText().toString();

                //Snackbar color
                int color = getResources().getColor(R.color.colorAccent,null);
                //Create snackbar for empty fields
                String noLastOrFirstNameText = getResources().getString(R.string.no_last_first_name_snackbar_text);
                Snackbar noLastOrFirstNameWarning = Snackbar
                        .make(view, noLastOrFirstNameText, Snackbar.LENGTH_LONG);
                noLastOrFirstNameWarning.setBackgroundTint(color);
                //Create worker added snackbar
                String workerAddedSnackText = getResources().getString(R.string.worker_added_snackbar_text);
                Snackbar workerAddedSnackbar = Snackbar
                        .make(view, workerAddedSnackText, Snackbar.LENGTH_LONG);
                workerAddedSnackbar.setBackgroundTint(color);

                if( !firstName.isEmpty() && !lastName.isEmpty() ){
                    Worker newWorker = new Worker(firstName, lastName);
                    mPersonnelManager.addWorker(newWorker);
                    workerAddedSnackbar.show();
                    mFirstNameET.getText().clear();
                    mLastNameET.getText().clear();
                }else{
                    noLastOrFirstNameWarning.show();
                }


            }
        });

        mCancelAddWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWorkerDialog.this.dismiss();
            }
        });
    }
}
