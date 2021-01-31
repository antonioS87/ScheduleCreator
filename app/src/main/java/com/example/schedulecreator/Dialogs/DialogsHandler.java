package com.example.schedulecreator.Dialogs;

import com.example.schedulecreator.Database.Worker;

public class DialogsHandler {

    public static  AddWorkerDialog getAddWorkerDialog(){

        return AddWorkerDialog.newInstance();
    }

    public static EditWorkerDialog getEditWorkerDialog(Worker worker){
        return EditWorkerDialog.newInstance(worker);
    }
}
