package com.example.boxyz.fragment.heat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.boxyz.R;
import com.example.boxyz.SocketClient;
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
        Heat heat = new Heat();
        Croller croller = view.findViewById(R.id.croller);
        ImageButton btn_state = view.findViewById(R.id.btn_state);
        TextView TextTemp = view.findViewById(R.id.temp_croller);
        List<TimeSlotItem> timeSlotList = new ArrayList<>();
        ListView timeSlotListView = view.findViewById(R.id.TimeSlotList);



        //set adapteur list view
        timeSlotList = heat.setList();
        timeSlotListView.setAdapter(new ItemAdapteur(getContext(), timeSlotList));

        //Croller
        croller.setProgress(heat.getHeat() - 15);
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                int temp = progress + 15;
                TextTemp.setText(temp + "°C");
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {
            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                SocketClient socket = new SocketClient();
                socket.execute("setHeat-" + Integer.valueOf(croller.getProgress() + 15));
            }
        });

        //Button On/Off
        btn_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketClient socket = new SocketClient();
                socket.execute("changeStatusHeater");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_heat_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 0);
                NavHostFragment.findNavController(HeatFragment.this).navigate(R.id.action_navigation_heat_to_navigation_heat_set, bundle);
            }
        });
    }

    public class ItemAdapteur extends BaseAdapter {

        private Context context;
        private List<TimeSlotItem> timeSlotItemList;
        private LayoutInflater inflater;

        public ItemAdapteur(Context context, List<TimeSlotItem> timeSlotItemList){
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

            view = inflater.inflate(R.layout.heat_time_slot_adapteur, null);

            TimeSlotItem currentItem  = getItem(i);
            Heat heat = new Heat();
            ImageButton btnEdit = view.findViewById(R.id.heat_btn_edit);
            ImageButton btnDel = view.findViewById(R.id.heat_btn_delete);
            Switch switch_state = view.findViewById(R.id.heat_switch);
            String itemName = currentItem.getName();
            String itemTemp = currentItem.getTemp();

            TextView itemNameView = view.findViewById(R.id.heat_time_name);
            itemNameView.setText(itemName);
            TextView itemTempView = view.findViewById(R.id.heat_time_temp_set);
            itemTempView.setText(itemTemp);

            switch_state.setChecked(heat.getState(i + 1));

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", i + 1);
                    NavHostFragment.findNavController(HeatFragment.this).navigate(R.id.action_navigation_heat_to_navigation_heat_set, bundle);
                }
            });
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SocketClient socket = new SocketClient();
                    socket.execute("stop");
                }
            });

            return view;
        }
    }


}