package com.divyanshusharma.futurealarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Divyanshu Sharma on 1/21/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Date> date=new ArrayList<Date>();
    private ArrayList<Boolean> onORoff=new ArrayList<Boolean>();
    private ArrayList<String> reminder=new ArrayList<String>();

    private Context context;


    public RecyclerAdapter(ArrayList<Date> date, ArrayList<Boolean> onORoff, ArrayList<String> reminder, Context context){

        this.date = date;
        this.onORoff = onORoff;
        this.reminder = reminder;

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

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

        viewHolder.dateTime.setText(date.get(i).toString());// viewHolder.itemTitle.setText(titles[i]);
        viewHolder.switchAlarm.setChecked(onORoff.get(i));
        viewHolder.reminder.setText(reminder.get(i));

    }

    @Override
    public int getItemCount() {
        return date.size();
    }




}
