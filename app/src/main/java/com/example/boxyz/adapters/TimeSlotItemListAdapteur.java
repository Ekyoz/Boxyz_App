package com.example.boxyz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.boxyz.R;
import com.example.boxyz.models.TimeSlotItem;

import java.util.List;

public class TimeSlotItemListAdapteur extends BaseAdapter {

    private Context context;
    private List<TimeSlotItem> timeSlotItemList;
    private LayoutInflater inflater;

    public TimeSlotItemListAdapteur(Context context, List<TimeSlotItem> timeSlotItemList){
        this.context = context;
        this.timeSlotItemList = timeSlotItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return timeSlotItemList.size();
    }

    @Override
    public TimeSlotItem getItem(int position) {
        return timeSlotItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.heat_time_slot_list_adapteur, null);

        TimeSlotItem currentItem  = getItem(i);
        String itemDay = currentItem.getDay();
        String itemHour = currentItem.getHour();
        String itemTemp = currentItem.getTemp();

        TextView itemDayView = view.findViewById(R.id.heat_time_list_days);
        itemDayView.setText(itemDay);
        TextView itemHourView = view.findViewById(R.id.heat_time_list_hours);
        itemHourView.setText(itemHour);
        TextView itemTempView = view.findViewById(R.id.heat_time_list_temp);
        itemTempView.setText(itemTemp);

        return view;
    }
}
