package com.example.boxyz;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.boxyz.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String reponse = null;
    int hour;
    int min;
    private static MainActivity instence;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        instence = this;


         //Try connect to serveur
        SocketClient socket = new SocketClient();
        socket.execute("test");
        try {
           reponse = socket.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        //Set content view
        if (reponse == null) {
            setContentView(R.layout.bad_serveur);
        } else {
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_light, R.id.navigation_heat, R.id.navigation_settings, R.id.navigation_remote)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        }
    }

    public static MainActivity getInstence() {
        return instence;
    }

    public void popTimePickerStart(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView temp = findViewById(R.id.tempStart);
                hour = hourOfDay;
                min = minute;
                temp.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, min, true);

        timePickerDialog.setTitle("Début");
        timePickerDialog.show();
    }

    public void popTimePickerEnd(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView temp = findViewById(R.id.tempEnd);
                hour = hourOfDay;
                min = minute;
                temp.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, min, true);

        timePickerDialog.setTitle("Fin");
        timePickerDialog.show();
    }
}