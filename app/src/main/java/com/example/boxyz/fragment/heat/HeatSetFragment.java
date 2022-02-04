package com.example.boxyz.fragment.heat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.boxyz.R;
import com.example.boxyz.adapters.DayPicker;
import com.google.android.material.textfield.TextInputEditText;

public class HeatSetFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static HeatSetFragment newInstance(String param1, String param2) {
        HeatSetFragment fragment = new HeatSetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HeatSetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heat_set, container, false);
        int position = getArguments().getInt("position");
        Heat heat = new Heat();
        //View
        Button btn_add = view.findViewById(R.id.btnAdd);
        Button btn_del = view.findViewById(R.id.btnDel);
        TextView timeTemp = view.findViewById(R.id.heat_time_temp_set);
        TextView timeStart = view.findViewById(R.id.tempStart);
        TextView timeEnd = view.findViewById(R.id.tempEnd);
        TextInputEditText inputName = view.findViewById(R.id.NameInput);
        DayPicker dayPicker = new DayPicker();
        //Varialbe
        int[] temp = {heat.getTemp(position)};

        //init view
        timeTemp.setText(temp[0] + "°C");
        inputName.setText(heat.getName(position));
        timeStart.setText(heat.getHourStart(position));
        timeEnd.setText(heat.getHourEnd(position));
        dayPicker.setCheck(view, position);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp[0] <= 24){
                    temp[0]++;
                    timeTemp.setText(temp[0] + "°C");
                }
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp[0] >= 16){
                    temp[0]--;
                    timeTemp.setText(temp[0] + "°C");
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}