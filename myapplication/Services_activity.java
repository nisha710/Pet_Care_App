package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Services_activity extends AppCompatActivity {

    Button adoreMe_button;
    Button care_button;
    Button shop_button;
    ImageButton services_prev_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_service);

        services_prev_btn = findViewById(R.id.services_back_arrow);
        adoreMe_button = findViewById(R.id.service_btn1);
        care_button = findViewById(R.id.service_btn2);
        shop_button = findViewById(R.id.service_btn3);

        services_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        adoreMe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_activity.this, Adore_Me_activity.class);
                startActivity(intent);
                finish();
            }
        });
        care_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_activity.this, CarePlansActivity.class);
                startActivity(intent);
                finish();
            }
        });

        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_activity.this, Shop_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}