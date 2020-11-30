package com.example.schedulecreator.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ScreenSlidePageAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> mFragmentList;

    public ScreenSlidePageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    public void setFragmentList( ArrayList<Fragment> fragmentList){
        mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("antonio", " ScreenSlidePageAdapter; createFragment(position); position: " + position + " fragment: " + mFragmentList.get(position).getTag());
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}