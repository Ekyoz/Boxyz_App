package com.ekyoz.boxyz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ekyoz.boxyz.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    FloatingActionButton powerFAB, powerHideFAB, powerOnFAB, powerOffFAB, editFAB;
    ConstraintLayout mainLayout;

    boolean fabShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_boiler, R.id.navigation_time_slot)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        mainLayout = findViewById(R.id.mainLayout);

        powerFAB = findViewById(R.id.power);
        powerHideFAB = findViewById(R.id.power_hide);
        powerOnFAB = findViewById(R.id.power_on);
        powerOffFAB = findViewById(R.id.power_off);
        editFAB = findViewById(R.id.edit_fab);

        powerHideFAB.hide();

        powerOnFAB.hide();
        powerOffFAB.hide();
        editFAB.hide();

        fabShow = false;

        powerFAB.setOnClickListener(view -> {
            if (fabShow){
                powerOnFAB.hide();
                powerOffFAB.hide();
                editFAB.hide();
                fabShow = false;
            }
            else {
                powerOnFAB.show();
                powerOffFAB.show();
                editFAB.show();
                fabShow = true;
            }
        });

        mainLayout.setOnClickListener(view -> {
            if (fabShow){
                powerOnFAB.hide();
                powerOffFAB.hide();
                editFAB.hide();
                fabShow = false;
            }
        });

    }

}