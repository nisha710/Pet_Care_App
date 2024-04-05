package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Adore_Me_activity extends AppCompatActivity{

    Button button1,button2,button3,button4,button5,button6;
    ImageButton adore_prev_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_adore_me);

        adore_prev_button = findViewById(R.id.adoreme_back_arrow);
        button1 = findViewById(R.id.adore_btn1);
        button2 = findViewById(R.id.adore_btn2);
        button3 = findViewById(R.id.adore_btn3);
        button4 = findViewById(R.id.adore_btn4);
        button5 = findViewById(R.id.adore_btn5);
        button6 = findViewById(R.id.adore_btn6);

        adore_prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Adore_Me_activity.this, Services_activity.class);
                startActivity(intent);
            }
        });

        //all buttons
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View1_activity.class);
                startActivity(i1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View2_activity.class);
                startActivity(i1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View3_activity.class);
                startActivity(i1);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View4_activity.class);
                startActivity(i1);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View5_activity.class);
                startActivity(i1);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adore_Me_activity.this, View6_activity.class);
                startActivity(i1);
            }
        });
    }
    public void goBackToService(View view) {
        // This method can be called when the "Back to Service" button is clicked.
        Intent intent = new Intent(Adore_Me_activity.this, MainActivity.class);
        startActivity(intent);
    }
}