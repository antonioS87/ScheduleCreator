package com.example.schedulecreator.fragments;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.R;
import com.example.schedulecreator.database.Worker;

import java.util.ArrayList;

public class PersonnelManagerRecyclerAdapter extends RecyclerView.Adapter<EditWorkerViewHolder> {

    private ArrayList<Worker> mWorkerList;

    public PersonnelManagerRecyclerAdapter(ArrayList<Worker> workerList){
        this.mWorkerList = workerList;
    }

    public void setWorkerList(ArrayList<Worker> mWorkerList) {
        this.mWorkerList = mWorkerList;
    }

    @NonNull
    @Override
    public EditWorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolderView = LayoutInflater.from( parent.getContext() ).
                inflate(R.layout.edit_personnel_item_view,parent, false );
        EditWorkerViewHolder viewHolder = new EditWorkerViewHolder( viewHolderView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditWorkerViewHolder holder, int position) {

        holder.initializeHolder( mWorkerList.get( position ) );

    }

    @Override
    public int getItemCount() {
        return mWorkerList.size();
    }
}
