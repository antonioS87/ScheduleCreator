package com.example.schedulecreator.dialogs;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;

public class DialogsHandler {

    public static  AddWorkerDialog getAddWorkerDialog(){

        return AddWorkerDialog.newInstance();
    }
}
