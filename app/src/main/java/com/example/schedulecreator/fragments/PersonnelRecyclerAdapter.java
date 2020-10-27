package com.example.schedulecreator.fragments;

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

    @NonNull
    @Override
    public PersonnelRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolderView = LayoutInflater.from( parent.getContext() ).
                inflate(R.layout.personnel_list_fragment_layout,parent, false );
        PersonnelRecyclerViewHolder viewHolder = new PersonnelRecyclerViewHolder( viewHolderView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonnelRecyclerViewHolder holder, int position) {
        holder.initializeHolder( mWorkerList.get( position ));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
