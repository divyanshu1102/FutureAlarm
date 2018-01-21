package com.divyanshusharma.futurealarm;

import java.util.Date;

/**
 * Created by Divyanshu Sharma on 1/21/2018.
 */

public class Alarm {

    Date date;
    Boolean aBoolean;
    String reminder;

    public Alarm(Date date,Boolean aBoolean,String reminder){

        this.aBoolean=aBoolean;
        this.date=date;
        this.reminder=reminder;
    }

    @Override
    public String toString()
    {
        String result=getDate()+" "+getaBoolean()+" "+getReminder();
        return result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
