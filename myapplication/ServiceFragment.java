package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class ServiceFragment extends Fragment {

    ImageButton services_prev_btn;
    Button adoreMe_button;
    Button care_button;
    Button shop_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service, container, false);
        services_prev_btn = rootView.findViewById(R.id.services_back_arrow);
        adoreMe_button = rootView.findViewById(R.id.service_btn1);
        care_button = rootView.findViewById(R.id.service_btn2);
        shop_button = rootView.findViewById(R.id.service_btn3);

        services_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getActivity(), MainActivity.class);
                startActivity(i1);
            }
        });
        adoreMe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getActivity(), Adore_Me_activity.class);
                startActivity(i1);
            }
        });
        care_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CarePlansActivity.class);
                startActivity(intent);
            }
        });
        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Shop_Activity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}