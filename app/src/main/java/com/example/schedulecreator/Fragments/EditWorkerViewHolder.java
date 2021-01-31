package com.example.schedulecreator.Fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.R;
import com.example.schedulecreator.Database.Worker;
import com.example.schedulecreator.Dialogs.DialogsHandler;

public class EditWorkerViewHolder extends RecyclerView.ViewHolder {

    private TextView mWorkerName;
    private ImageView mEditWorkerButton;
    private Worker mWorker;



    public EditWorkerViewHolder(@NonNull View itemView) {
        super(itemView);
        mEditWorkerButton = itemView.findViewById( R.id.edit_worker_button);
        mWorkerName = itemView.findViewById( R.id.first_last_name);

    }

    public void initializeHolder(Worker worker, FragmentManager fragmentManager) {
        mWorker = worker;
        mWorkerName.setText(mWorker.getFirstName() + " " + mWorker.getLastName());

        mEditWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogsHandler.getEditWorkerDialog(mWorker).show(fragmentManager, "edit_worker_dialog_fragment");
            }
        });

    }
}
