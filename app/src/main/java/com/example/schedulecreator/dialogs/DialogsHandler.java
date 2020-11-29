package com.example.schedulecreator.dialogs;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.schedulecreator.Interfaces.PersonnelManager;
import com.example.schedulecreator.Interfaces.PersonnelRepoManager;
import com.example.schedulecreator.database.Worker;

public class DialogsHandler {

    public static  AddWorkerDialog getAddWorkerDialog(){

        return AddWorkerDialog.newInstance();
    }

    public static EditWorkerDialog getEditWorkerDialog(Worker worker){
        return EditWorkerDialog.newInstance(worker);
    }
}
