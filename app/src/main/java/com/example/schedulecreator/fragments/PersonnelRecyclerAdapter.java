package com.example.schedulecreator.fragments;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.R;

import java.util.ArrayList;

public class PersonnelRecyclerAdapter extends RecyclerView.Adapter<PersonnelRecyclerViewHolder> {

    private ArrayList<Worker> mWorkerList;

    public PersonnelRecyclerAdapter(ArrayList<Worker> workerList){
        this.mWorkerList = workerList;
    }

    public void setWorkerList(ArrayList<Worker> mWorkerList) {
        this.mWorkerList = mWorkerList;
    }

    @NonNull
    @Override
    public PersonnelRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolderView = LayoutInflater.from( parent.getContext() ).
                inflate(R.layout.personnel_list_item,parent, false );
        PersonnelRecyclerViewHolder viewHolder = new PersonnelRecyclerViewHolder( viewHolderView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonnelRecyclerViewHolder holder, int position) {
        Worker worker = mWorkerList.get( position );
        Resources resources = holder.itemView.getResources();
        holder.initializeHolder( worker, resources );

    }

    @Override
    public int getItemCount() {
        return mWorkerList.size();
    }
}
