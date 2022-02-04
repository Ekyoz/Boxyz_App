package com.example.boxyz.adapters;

import android.view.View;
import android.widget.CheckBox;

import com.example.boxyz.R;
import com.example.boxyz.fragment.heat.Heat;

import org.json.JSONArray;

public class DayPicker {

    public void setCheck(View view, int position) {
        CheckBox checkLundi = view.findViewById(R.id.checkBox_Lundi);
        CheckBox checkMardi = view.findViewById(R.id.checkBox_Mardi);
        CheckBox checkMercredi = view.findViewById(R.id.checkBox_Mercredi);
        CheckBox checkJeudi = view.findViewById(R.id.checkBox_Jeudi);
        CheckBox checkVendredi = view.findViewById(R.id.checkBox_Vendredi);
        CheckBox checkSamedi = view.findViewById(R.id.checkBox_Samedi);
        CheckBox checkDimanche = view.findViewById(R.id.checkBox_Dimanche);
        Heat heat = new Heat();
        JSONArray day = heat.getDay(position);

        if (day.toString().contains("Lundi")){
            checkLundi.setChecked(true);
        }
        if (day.toString().contains("Mardi")){
            checkMardi.setChecked(true);
        }
        if (day.toString().contains("Mercredi")){
            checkMercredi.setChecked(true);
        }
        if (day.toString().contains("Jeudi")){
            checkJeudi.setChecked(true);
        }
        if (day.toString().contains("Vendredi")){
            checkVendredi.setChecked(true);
        }
        if (day.toString().contains("Samedi")){
            checkSamedi.setChecked(true);
        }
        if (day.toString().contains("Dimanche")){
            checkDimanche.setChecked(true);
        }
    }
}
