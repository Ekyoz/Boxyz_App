package com.ekyoz.boxyz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ekyoz.boxyz.R;
import com.ekyoz.boxyz.databinding.FragmentBoilerBinding;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

public class BoilerFragment extends Fragment {

    private FragmentBoilerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boiler, container, false);

        Croller croller = view.findViewById(R.id.croller);
        TextView editTemp = view.findViewById(R.id.temp_croller);

        croller.setProgress(1);
        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                editTemp.setText(String.valueOf(progress +14 + "°C"));
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {
            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}