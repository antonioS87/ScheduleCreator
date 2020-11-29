package com.example.schedulecreator.fragments;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.R;
import com.example.schedulecreator.database.Worker;

public class EditWorkerViewHolder extends RecyclerView.ViewHolder {

    private TextView mWorkerName;
    private ImageView mEditWorkerButton;



    public EditWorkerViewHolder(@NonNull View itemView) {
        super(itemView);

        mEditWorkerButton = itemView.findViewById( R.id.edit_worker_button);
        mWorkerName = itemView.findViewById( R.id.first_last_name);

    }

    public void initializeHolder(Worker worker) {
        String workerName = worker.getFirstName() + " " + worker.getLastName();
        mWorkerName.setText(workerName);


    }
}
