package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DrillInfoActivity extends AppCompatActivity {

    TextView infoText;
    Button launchARBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_info);

        infoText = findViewById(R.id.textDrillInfo);
        launchARBtn = findViewById(R.id.buttonLaunchAR);

        String drillName = getIntent().getStringExtra("DRILL_NAME");
        infoText.setText("You selected: " + drillName + "\n\nDescription:\nDummy data.\n\nTips:\n• Tip 1\n• Tip 2");

        launchARBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DrillInfoActivity.this, ARActivity.class);
            startActivity(intent);
        });
    }
}

