package com.divyanshusharma.futurealarm;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.security.spec.ECField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Divyanshu Sharma on 1/21/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    /*
    private ArrayList<Date> date=new ArrayList<Date>();
    private ArrayList<Boolean> onORoff=new ArrayList<Boolean>();
    private ArrayList<String> reminder=new ArrayList<String>();
    */
    private ArrayList<Alarm> alarm;
    private Context context;

    /*
    public RecyclerAdapter(ArrayList<Date> date, ArrayList<Boolean> onORoff, ArrayList<String> reminder, Context context){

        this.date = date;
        this.onORoff = onORoff;
        this.reminder = reminder;

        this.context = context;
    }*/

    public RecyclerAdapter(ArrayList<Alarm> alarm_arraylist, Context context){

        this.alarm = alarm_arraylist;

        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dateTime;
        public Switch switchAlarm;
        public TextView reminder;

        public ViewHolder(View itemView) {

            super(itemView);
            dateTime = (TextView) itemView.findViewById(R.id.dateTime);
            switchAlarm = (Switch)itemView.findViewById(R.id.switchAlarm);
            reminder=(TextView)itemView.findViewById(R.id.reminder);

            switchAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(!switchAlarm.isChecked())
                    {
                        dateTime.setTextColor(Color.parseColor("#b3b3b3"));
                        reminder.setTextColor(Color.parseColor("#b3b3b3"));

                    }
                    else
                    {
                        dateTime.setTextColor(Color.parseColor("#ffffff"));
                        reminder.setTextColor(Color.parseColor("#ffffff"));

                    }

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    alarm.get(position).setaBoolean(!switchAlarm.isChecked());

                    /*
                    Bundle mBundle = new Bundle();
                    mBundle.putString("Major", details.get(position));
                    mBundle.putString("Name", images.get(position));
                    mBundle.putString("Phone", phoneNumbers.get(position));
                    mBundle.putString("Gender", gender.get(position));
                    mBundle.putString("Lifestyle", lifestyle.get(position));
                    mBundle.putString("Cleaning", cleaning.get(position));
                    mBundle.putString("Bio", bio.get(position));
                    mBundle.putString("Guest", guest.get(position));

                    context.startActivity(new Intent(context, Communication.class).putExtras(mBundle));
                    */
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alarm_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd HH:mm");
        String outputDate="";
        try
        {
            outputDate = formatter.format(alarm.get(i).getDate());
        }
        catch (Exception e) {
            Log.i("DateError","Error while date to string");
        }


        viewHolder.switchAlarm.setChecked(alarm.get(i).getaBoolean());

        if(!viewHolder.switchAlarm.isChecked())
        {
            viewHolder.dateTime.setTextColor(Color.parseColor("#b3b3b3"));
            viewHolder.reminder.setTextColor(Color.parseColor("#b3b3b3"));
        }
        else
        {
            viewHolder.dateTime.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.reminder.setTextColor(Color.parseColor("#ffffff"));
        }

        viewHolder.dateTime.setText(outputDate);
        viewHolder.reminder.setText(alarm.get(i).getReminder());

    }

    @Override
    public int getItemCount() {
        return alarm.size();
    }




}
