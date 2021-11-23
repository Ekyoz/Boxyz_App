package com.example.boxyz.fragment.heat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.boxyz.R;
import com.example.boxyz.adapters.TimeSlotItemListAdapteur;
import com.example.boxyz.models.TimeSlotItem;

import java.util.ArrayList;
import java.util.List;

public class HeatListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static HeatListFragment newInstance(String param1, String param2) {
        HeatListFragment fragment = new HeatListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HeatListFragment() {
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
        //View
        View view = inflater.inflate(R.layout.fragment_heat_list, container, false);
        //Variable
        List<TimeSlotItem> timeSlotList = new ArrayList<>();
        ListView timeSlotListView = view.findViewById(R.id.TimeSlotList);

        //Boucle for add element in list view
        timeSlotList.add(new TimeSlotItem("Lundi", "18:00 - 19:00","21,5°C", true));
        timeSlotList.add(new TimeSlotItem("Mardi", "15:00 - 16:00","19°C", true));
        timeSlotList.add(new TimeSlotItem("Mercredi", "12:00 - 15:00", "19°C", false));
        timeSlotListView.setAdapter(new TimeSlotItemListAdapteur(getContext(), timeSlotList));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_heat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HeatListFragment.this)
                        .navigate(R.id.action_navigation_heat_time_to_navigation_heat);
            }
        });
        view.findViewById(R.id.btn_heat_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 1);
                NavHostFragment.findNavController(HeatListFragment.this)
                        .navigate(R.id.action_navigation_heat_time_to_navigation_heat_set, bundle);
            }
        });

    }
}