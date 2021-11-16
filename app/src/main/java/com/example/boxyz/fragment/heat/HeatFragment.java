package com.example.boxyz.fragment.heat;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.boxyz.R;
import com.example.boxyz.SocketClient;
import com.example.boxyz.adapters.TimeSlotItemAdapteur;
import com.example.boxyz.models.TimeSlotItem;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HeatFragment extends Fragment {

    public String reponse;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heat, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Croller croller = view.findViewById(R.id.croller);
        Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT);
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

        ImageButton button = view.findViewById(R.id.btn_status);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketClient send = new SocketClient();
                send.execute("heatStatus");
                try {
                    reponse = send.get();
                } catch (ExecutionException | InterruptedException e) {
                    Toast.makeText(getContext(),"Le serveur ne repond pas !", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        List<TimeSlotItem> timeSlotListList = new ArrayList<>();
        timeSlotListList.add(new TimeSlotItem("Samedi", "19:30 - 21:30", "21°C"));
        timeSlotListList.add(new TimeSlotItem("Dimanche", "19:30 - 21:30", "21°C"));
        timeSlotListList.add(new TimeSlotItem("Lundi", "19:30 - 21:30", "21°C"));

        ListView timeSlotView = view.findViewById(R.id.TimeSlotList);
        timeSlotView.setAdapter(new TimeSlotItemAdapteur(getContext(), timeSlotListList));

        return view;
    }
}