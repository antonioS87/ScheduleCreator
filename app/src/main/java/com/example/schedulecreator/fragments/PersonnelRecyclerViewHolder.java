package com.example.schedulecreator.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.R;

public class PersonnelRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View.OnClickListener mClickListener;
    private TextView mWorkerNameTV;
    private TextView mWorkerSurnameTV;

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public PersonnelRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mWorkerNameTV = itemView.findViewById( R.id.worker_name_tv);
        mWorkerSurnameTV = itemView.findViewById( R.id.worker_surname_tv);

    }

    @Override
    public void onClick(View view) {
        if( mClickListener != null ){
            mClickListener.onClick( view );
        }
    }


    public void initializeHolder(Worker worker) {

    }
}
