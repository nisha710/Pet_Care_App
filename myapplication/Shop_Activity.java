package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Shop_Activity extends AppCompatActivity {

    Button cbtn1,cbtn2,cbtn3,dbtn1,dbtn2,dbtn3;
    ImageButton viewcart;

    RecyclerView recyclerView;
    MainAdapter cartAdapter;
    ArrayList list;

    ImageButton btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop1);

        cbtn1=(Button) findViewById(R.id.cat1btn);
        cbtn2=(Button) findViewById(R.id.cat2btn);
        cbtn3=(Button) findViewById(R.id.cat3btn);

        dbtn1=(Button) findViewById(R.id.dog1btn);
        dbtn2=(Button) findViewById(R.id.dog2btn);
        dbtn3=(Button) findViewById(R.id.dog3btn);

        //fish1=(Button) findViewById(R.id.fish1btn);

        viewcart=(ImageButton) findViewById(R.id.viewcart);
        btn = (ImageButton) findViewById(R.id.back_arrow);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shop_Activity.this,Services_activity.class);
                startActivity(intent);
            }
        });


        cbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_Activity.this, Cat1.class);
                startActivity(intent);
            }
        });

        cbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Shop_Activity.this, Cat2.class);
                startActivity(intent);

            }
        });

        cbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Shop_Activity.this, Cat3.class);
                startActivity(intent);

            }
        });

        dbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_Activity.this, Dog1.class);
                startActivity(intent);
            }
        });

        dbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_Activity.this, Dog2.class);
                startActivity(intent);
            }
        });

        dbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_Activity.this, Dog3.class);
                startActivity(intent);
            }
        });

        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Shop_Activity.this, ViewCart.class);
                startActivity(intent);
            }
        });


    }
}