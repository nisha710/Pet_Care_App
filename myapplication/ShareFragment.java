
package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShareFragment extends Fragment {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Button btLocation;
    private TextView textView1, textView2, textView3, textView4, textView5;
    double latitude, longitude;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);

        // Initialize variables
        btLocation = rootView.findViewById(R.id.bt_location);
        textView1 = rootView.findViewById(R.id.text_view1);
        textView2 = rootView.findViewById(R.id.text_view2);
        textView3 = rootView.findViewById(R.id.text_view3);
        textView4 = rootView.findViewById(R.id.text_view4);
        textView5 = rootView.findViewById(R.id.text_view5);

        // Initializing fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        // Set an OnClickListener for the button
        btLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check permission
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    // When permission is denied, request it
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

        return rootView;
    }

    // Create a method to calculate the distance
    private double calculateDistance(double targetLatitude, double targetLongitude, Location currentLocation) {
        if (currentLocation == null) {
            return -1; // Return -1 if the current location is unavailable
        }

        // Create a Location object for the target location
        Location targetLocation = new Location("");
        targetLocation.setLatitude(targetLatitude);
        targetLocation.setLongitude(targetLongitude);

        // Calculate the distance using the Haversine formula
        float distance = currentLocation.distanceTo(targetLocation);

        // Convert distance to kilometers (optional)
        double distanceInKm = distance / 1000.0;

        return distanceInKm;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Handle permission requests or provide feedback to the user.
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        // Initializing geoCoder
                        Geocoder geocoder = new Geocoder(requireContext(), Locale.US);

                        // Initializing address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        // Set Latitude on textView
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude :</b><br></font>" + addresses.get(0).getLatitude()));
                        // Set Longitude on textView
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude :</b><br></font>" + addresses.get(0).getLongitude()));
                        // Set country name
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country :</b><br></font>" + addresses.get(0).getCountryName()));
                        // Set Locality
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality :</b><br></font>" + addresses.get(0).getLocality()));
                        // Set address
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</b><br></font>" + addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}