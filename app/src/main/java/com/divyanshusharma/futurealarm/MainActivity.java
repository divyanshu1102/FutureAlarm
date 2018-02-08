package com.divyanshusharma.futurealarm;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    private ArrayList<Alarm> alarms= new ArrayList<Alarm>();
    private int newYear=-1, newMonth=-1, newDay=-1, newHour=-1, newMinute=-1;
    private TextView check;
    private String reminder="", allSavedAlarms="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check= (TextView) findViewById(R.id.Check);

        sharedpreferences= getPreferences(MODE_PRIVATE);

        /*
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        Gson gson = new Gson();
        Alarm testingAlarm=new Alarm(new Date(),false,"Shared Pref worked");
        String json = gson.toJson(testingAlarm); // myObject - instance of MyObject
        prefsEditor.putString("Saved Alarms", json);
        prefsEditor.commit();
        */

        Gson gson1= new Gson();
        if(!sharedpreferences.getAll().isEmpty()) // if sharedpreferences is not empty
        {
            String json1= sharedpreferences.getString("Saved Alarms","");
            check.setText(json1);
            allSavedAlarms=json1;

        }
        else
            Toast.makeText(this,"Empty Shared Preferences",Toast.LENGTH_LONG).show();



        // render all existing alarms
        /*
        if(!alarms.isEmpty())
            check.setText(alarms.toString());
         */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePicker(view); // creating an alarm

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

                if( VerifyData(newDay,newMonth,newYear, newHour, newMinute) )// true
                {
                    getReminderInput();

                }
                else
                {
                    Snackbar.make(tempView, "Wrong Input", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.show();

    }

    public void saveSharedPreferences(){


    }

    public void updateDisplayedAlarms(){



    }

    public void getReminderInput(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Reminder: ");

        final AutoCompleteTextView input = new AutoCompleteTextView(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reminder = input.getText().toString();

                Date tempDate= new Date(newYear-1900,newMonth,newDay,newHour,newMinute,0);
                Alarm tempAlarm=new Alarm(tempDate,true,reminder);
                alarms.add(tempAlarm);

                ////////////////
                SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(alarms.toString()); // myObject - instance of MyObject
                prefsEditor.putString("Saved Alarms", json);
                prefsEditor.commit();
                /////////////

                check.setText(alarms.toString());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


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

                newYear=selectedyear;
                newMonth=selectedmonth;
                newDay=selectedday;

                showTimePicker(tempView);

            }
        }, mYear, mMonth, mDay);

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
