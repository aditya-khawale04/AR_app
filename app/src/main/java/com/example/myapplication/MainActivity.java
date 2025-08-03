package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner drillSpinner;
    Button startDrillBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drillSpinner = findViewById(R.id.spinnerDrills);
        startDrillBtn = findViewById(R.id.buttonStartDrill);

        String[] drills = {"Drill 1", "Drill 2", "Drill 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, drills);
        drillSpinner.setAdapter(adapter);

        startDrillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDrill = drillSpinner.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, DrillInfoActivity.class);
                intent.putExtra("DRILL_NAME", selectedDrill);
                startActivity(intent);
            }
        });
    }
}
