package com.divyanshusharma.futurealarm;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

        private ArrayList<Alarm> Alarms = new ArrayList<Alarm>();
    private int newYear=-1, newMonth=-1, newDay=-1, newHour=-1, newMinute=-1;
    private TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        check= (TextView) findViewById(R.id.Check);

        if(!Alarms.isEmpty())
        {
            check.setText(Alarms.toString());
        }

        //SoundAlarm();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                //final View tempView= view;

                //showTimePicker(view);
                showDatePicker(view);


                /*
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        newHour=selectedHour;
                        newMinute=selectedMinute;

                        if(  VerifyData(newDay,newMonth,newYear, newHour, newMinute) )// true
                        {
                            // initialize a new Date and add it to the arraylist
                            Date tempDate= new Date(newYear-1900,newMonth,newDay,newHour,newMinute,0);
                            Alarm tempAlarm=new Alarm(tempDate,true,"reminder");
                            Alarms.add(tempAlarm);
                            check.setText(Alarms.toString());


                            // add more code here




                        }
                        else
                        {
                            Snackbar.make(tempView, "Wrong Input", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }

                    }
                }, hour, minute, false);//Yes 24 hour time
                //mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                */

                /*
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        //selectedmonth = selectedmonth + 1;
                        newYear=selectedyear;
                        newMonth=selectedmonth;
                        newDay=selectedday;

                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select Date");
                mDatePicker.show();
                */
        }
        });

    }

    public void showTimePicker(View view){

        final View tempView=view;
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                newHour=selectedHour;
                newMinute=selectedMinute;

                if(  VerifyData(newDay,newMonth,newYear, newHour, newMinute) )// true
                {
                    // initialize a new Date and add it to the arraylist
                    Date tempDate= new Date(newYear-1900,newMonth,newDay,newHour,newMinute,0);
                    Alarm tempAlarm=new Alarm(tempDate,true,"reminder");
                    Alarms.add(tempAlarm);
                    check.setText(Alarms.toString());


                    // add more code here




                }
                else
                {
                    Snackbar.make(tempView, "Wrong Input", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        }, hour, minute, false);//Yes 24 hour time
        //mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    public void showDatePicker(View view)
    {

        final View tempView=view;
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                //selectedmonth = selectedmonth + 1;
                newYear=selectedyear;
                newMonth=selectedmonth;
                newDay=selectedday;

                showTimePicker(tempView);

            }
        }, mYear, mMonth, mDay);
        //mDatePicker.setTitle("Select Date");
        mDatePicker.show();

    }

    public boolean VerifyData(int day, int month, int year, int hour, int minute)
    {
        Date currentDate= new Date();
        Log.i("Data","Current Date: "+currentDate+" "+day+" "+month+" "+year+" "+hour+" "+minute);

        if(day==-1 || month==-1 || year==-1 || hour==-1 || minute==-1)
        {
            return false;
        }

        Date temp = new Date(year-1900,month,day,hour,minute,0);
        Log.i("Data","Obtained Date: "+temp);
        if(temp.before(currentDate))
            return false;

        return true;
    }

    /*
    public void SoundAlarm()
    {
        Toast.makeText(MainActivity.this,"Inside SoundAlarm",Toast.LENGTH_SHORT).show();
        if(!Alarms.isEmpty()) {

            Date currentDate = new Date();

            if(Alarms.contains(currentDate))
            {
                //Log.i("Alarm","Alarm was sounded");
                Toast.makeText(MainActivity.this,"Alarm Rings",Toast.LENGTH_LONG).show();
            }
        }
        else
            return;

    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
