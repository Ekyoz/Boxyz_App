package com.ekyoz.boxyz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ekyoz.boxyz.R;
import com.ekyoz.boxyz.adapteur.timeSlotAdapteur.ItemDayAdapteur;
import com.ekyoz.boxyz.databinding.FragmentTimeSlotBinding;
import com.ekyoz.boxyz.models.timeSlotModels.ItemDayModel;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotFragment extends Fragment {

    private FragmentTimeSlotBinding binding;

    private List<ItemDayModel> heatTimeList;
    private ListView heatTimeView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_slot, container, false);

        heatTimeList = new ArrayList<>();
        //heatTimeView = view.findViewById(R.id.);

        heatTimeList.add(new ItemDayModel(20, "test"));
        heatTimeList.add(new ItemDayModel(20, "test"));
        heatTimeList.add(new ItemDayModel(20, "test"));
        heatTimeView.setAdapter(new ItemDayAdapteur(getContext(), heatTimeList));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}