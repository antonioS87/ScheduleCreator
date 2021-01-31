package com.example.schedulecreator.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.R;
import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.Database.Worker;
import com.google.android.material.snackbar.Snackbar;
// ...

public class AddWorkerDialog extends DialogFragment {

    private EditText mFirstNameET;
    private EditText mLastNameET;
    private Button mAddWorkerButton;
    private Button mCancelAddWorkerButton;
    private PersonnelRepoManager mPersonnelManager;

    public AddWorkerDialog() {

    }

    public static AddWorkerDialog newInstance() {
        AddWorkerDialog addWorkerDialog = new AddWorkerDialog();
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
        mPersonnelManager = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
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
