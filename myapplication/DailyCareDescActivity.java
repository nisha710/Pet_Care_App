package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DailyCareDescActivity extends AppCompatActivity {

    ImageButton prev_btn;
    Button buy_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_care_desc);
        prev_btn = (ImageButton) findViewById(R.id.plans_back_arrow);
        buy_btn = (Button) findViewById(R.id.buyweekend);

        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailyCareDescActivity.this,CarePlansActivity.class);
                startActivity(intent);
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show the custom dialog
                Dialog customDialog = new Dialog(DailyCareDescActivity.this);
                customDialog.setContentView(R.layout.activity_care_plans_buy);

                // Find the buttons in the dialog layout
                Button buttonOk = customDialog.findViewById(R.id.button_ok);
                Button buttonCancel = customDialog.findViewById(R.id.button_cancel);

                // Set click listeners for the buttons
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle OK button click
                        customDialog.dismiss(); // Close the dialog
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Cancel button click
                        customDialog.dismiss(); // Close the dialog
                    }
                });
                customDialog.show();
            }
        });
    }

}