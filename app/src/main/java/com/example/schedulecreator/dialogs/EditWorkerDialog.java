package com.example.schedulecreator.dialogs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.R;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.database.Worker;
import com.google.android.material.snackbar.Snackbar;
// ...

public class EditWorkerDialog extends DialogFragment {

    private EditText mFirstNameET;
    private EditText mLastNameET;
    private Button mSaveChangesButton;
    private Button mCancelEditWorkerButton;
    private PersonnelRepoManager mPersonnelManager;
    private Worker mWorker;

    public EditWorkerDialog() {

    }

    public static EditWorkerDialog newInstance(Worker worker) {
        EditWorkerDialog editWorkerDialog = new EditWorkerDialog();
        editWorkerDialog.setWorker(worker);
        return editWorkerDialog;
    }

    private void setWorker(Worker worker) {
        mWorker = worker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_worker_dialog_fragmet, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mPersonnelManager = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        mFirstNameET = view.findViewById(R.id.new_worker_firstName_et);
        mLastNameET = view.findViewById(R.id.new_worker_lastName_et);
        mSaveChangesButton = view.findViewById(R.id.save_chages_button);
        mCancelEditWorkerButton = view.findViewById(R.id.cancel_edit_worker_button);

        //Initialize Edit texts for last and first name
        mFirstNameET.setText(mWorker.getFirstName());
        mLastNameET.setText(mWorker.getLastName());

        // Show soft keyboard automatically and request focus to field
        mFirstNameET.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mSaveChangesButton.setOnClickListener(new View.OnClickListener() {
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
                String workerUpdatedSnackbarText = getResources().getString(R.string.worker_updated_warning_text);
                Snackbar workerAddedSnackbar = Snackbar
                        .make(view, workerUpdatedSnackbarText, Snackbar.LENGTH_LONG);
                workerAddedSnackbar.setBackgroundTint(color);

                if( !firstName.isEmpty() && !lastName.isEmpty() ){
                    mWorker.setFirstName(firstName);
                    mWorker.setLastName(lastName);
                    mPersonnelManager.updateWorker(mWorker);
                    workerAddedSnackbar.show();

                }else{
                    noLastOrFirstNameWarning.show();
                }


            }
        });

        mCancelEditWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditWorkerDialog.this.dismiss();
            }
        });
    }
}
