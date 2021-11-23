package com.example.boxyz.fragment.heat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.boxyz.R;
import com.example.boxyz.adapters.TimeSlotItemAdapteur;
import com.example.boxyz.models.TimeSlotItem;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

import java.util.ArrayList;
import java.util.List;

public class HeatFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public HeatFragment() {
    }

    public static HeatFragment newInstance(String param1, String param2) {
        HeatFragment fragment = new HeatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        //View
        View view = inflater.inflate(R.layout.fragment_heat, container, false);
        //Variables
        ListView timeSlotListView = view.findViewById(R.id.TimeSlotList);
        //Heat heat = new Heat();
        Croller croller = view.findViewById(R.id.croller);
        ImageButton btn_state = view.findViewById(R.id.btn_state);
        List<TimeSlotItem> timeSlotList = new ArrayList<>();

        //set adapteur list view
        //heat.getList();
        timeSlotList.add(new TimeSlotItem("Lundi", "18:00 - 19:00","21,5°C", true));
        timeSlotList.add(new TimeSlotItem("Mardi", "15:00 - 16:00","19°C", true));
        timeSlotList.add(new TimeSlotItem("Mercredi", "12:00 - 15:00", "19°C", false));

        timeSlotListView.setAdapter(new TimeSlotItemAdapteur(getContext(), timeSlotList));

        //Croller
        croller.setProgress(5);
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                TextView textView = view.findViewById(R.id.temp_croller);
                int temp = progress + 15;
                textView.setText(temp + "°C");
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {
            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
            }
        });

        //Button On/Off
        btn_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //click listener for change fragmant to heat set
        timeSlotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                NavHostFragment.findNavController(HeatFragment.this).navigate(R.id.action_navigation_heat_to_navigation_heat_set, bundle);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_heat_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HeatFragment.this).navigate(R.id.action_navigation_heat_to_navigation_heat_time);
            }
        });
    }

    private void makeToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}