package com.example.schedulecreator.dialogs;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

public class DialogsHandler {

    public static  AddWorkerDialog getAddWorkerDialog(String title){

        return AddWorkerDialog.newInstance(title);
    }
}
