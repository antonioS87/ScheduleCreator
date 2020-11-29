package com.example.schedulecreator.dialogs;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.schedulecreator.Interfaces.PersonnelManager;

public class DialogsHandler {

    public static  AddWorkerDialog getAddWorkerDialog(PersonnelManager personnelManager){

        return AddWorkerDialog.newInstance(personnelManager);
    }
}
