package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CarePlansActivity extends AppCompatActivity {

    ImageButton care_prev_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_plans);

        care_prev_button = findViewById(R.id.care_back_arrow);

        care_prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarePlansActivity.this, Services_activity.class);
                startActivity(intent);
            }
        });

    }

    public void GoToTrialPlan(View v)
    {
        Intent trialPlan_intent = new Intent(this,trialPlanDescActivity.class);
        startActivity(trialPlan_intent);
    }

    public void GoToDailyCarePlan(View v)
    {
        Intent DailyCare_intent = new Intent(this,DailyCareDescActivity.class);
        startActivity(DailyCare_intent);
    }

    public void GoToWeekendPlan(View v)
    {
        Intent Weekend_intent = new Intent(this,weekendGatewayActivity.class);
        startActivity(Weekend_intent);
    }

    public void GoToVacationPlan(View v)
    {
        Intent vacation_intent = new Intent(this,VacationActivity.class);
        startActivity(vacation_intent);
    }

    public void GoToMedicalPlan(View v)
    {
        Intent medical_intent = new Intent(this,MedicalCareActivity.class);
        startActivity(medical_intent);
    }

    public void GoToDietPlan(View v)
    {
        Intent diet_intent = new Intent(this,SpecialDietActivity.class);
        startActivity(diet_intent);
    }




}