package com.divyanshusharma.futurealarm;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Divyanshu Sharma on 1/21/2018.
 */

public class Alarm {

    Date date;
    Boolean aBoolean;
    String reminder;

    public Alarm(String compositeAlarm)
    {
        String[] splitStrings=compositeAlarm.split("-");

        SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd HH:mm:ss yyyy");

        try
        {
            this.date= formatter.parse(splitStrings[0]);
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.i("DateError","Error while converting string to date");
        }

        this.aBoolean=Boolean.parseBoolean(splitStrings[1]);
        this.reminder=splitStrings[2];

    }

    /*
    public Alarm(String stringDate,String stringABoolean, String stringReminder){

        SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd HH:mm:ss yyyy");

        try
        {
            this.date= formatter.parse(stringDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.i("DateError","Error while converting string to date");
        }

        this.aBoolean=Boolean.parseBoolean(stringABoolean);
        this.reminder=stringReminder;

    }
    */

    public Alarm(Date date,Boolean aBoolean,String reminder){

        this.aBoolean=aBoolean;
        this.date=date;
        this.reminder=reminder;
    }
    public Alarm(){}


    @Override
    public String toString()
    {
        String result=getDate()+"-"+getaBoolean()+"-"+getReminder();
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
