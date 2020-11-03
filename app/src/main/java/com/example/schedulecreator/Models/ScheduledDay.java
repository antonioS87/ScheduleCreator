package com.example.schedulecreator.Models;

import java.util.Date;

public class ScheduledDay {
    private Date mDate;
    private String mWorkerId;

    public ScheduledDay( Date date, String workerId){
        mDate = date;
        mWorkerId = workerId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getWorkerId() {
        return mWorkerId;
    }

    public void setWorkerID(String mWorkerId) {
        this.mWorkerId = mWorkerId;
    }
}
