package com.ekyoz.boxyz.adapteur.timeSlotAdapteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ekyoz.boxyz.R;
import com.ekyoz.boxyz.models.timeSlotModels.ItemDayModel;

import java.util.List;

public class ItemDayAdapteur extends BaseAdapter {

    private Context context;
    private List<ItemDayModel> timeSlotItemList;
    private LayoutInflater inflater;

    public ItemDayAdapteur(Context context, List<ItemDayModel> timeSlotItemList){
        this.context = context;
        this.timeSlotItemList = timeSlotItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return timeSlotItemList.size();
    }

    @Override
    public ItemDayModel getItem(int position) {
        return timeSlotItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.item_day_adapteur, null);

        ItemDayModel currentItem  = getItem(i);
        ImageButton btnEdit = view.findViewById(R.id.heat_btn_edit);
        ImageButton btnDel = view.findViewById(R.id.heat_btn_delete);
        Switch switch_state = view.findViewById(R.id.heat_switch);
        String itemName = currentItem.getName();
        int itemTemp = currentItem.getTemp();

        TextView itemNameView = view.findViewById(R.id.heat_time_name);
        itemNameView.setText(itemName);
        TextView itemTempView = view.findViewById(R.id.heat_time_temp_set);
        itemTempView.setText(String.valueOf(itemTemp));

        return view;
    }


}
