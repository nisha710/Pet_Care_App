package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    MainAdapter cartAdapter;
    ArrayList list;
    Button payment ;
    //TextView textView;
    Firebase auth;

    TextView totalCostTextView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cart);


        list=new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        totalCostTextView=findViewById(R.id.total_cost);

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Use LinearLayoutManager or another layout manager

        fetchCartItems();

        payment= findViewById(R.id.payment);
        TextView cost=findViewById(R.id.total_cost);

        //textView = (TextView) findViewById(R.id.setemail);



    }

    private void fetchCartItems() {

        list.clear(); // Clear the list before fetching data

        // Reference to the "AddToCart" collection
        CollectionReference addToCartCollection = FirebaseFirestore.getInstance().collection("AddToCart");

        // Query the collection for cart items with a specific email
        addToCartCollection.whereEqualTo("email", "sakshi@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Convert the document to a MainModel object
                                MainModel mainModel = document.toObject(MainModel.class);
                                mainModel.setItemId(document.getId());
                                list.add(mainModel);
                            }

                            // Calculate the total cost
                            double totalCost = calculateTotalCost(list);
                            onTotalCostCalculated(totalCost);
                            // Display the total cost in the TextView

                            // Initialize the adapter and set it to the RecyclerView
                            cartAdapter = new MainAdapter(ViewCart.this, list);
                            recyclerView.setAdapter(cartAdapter);


                        } else {
                            Toast.makeText(ViewCart.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onTotalCostCalculated(double totalCost) {

        // Display the total cost in the TextView
        totalCostTextView.setText("Total Cost: Rs " + totalCost);

        // Now that the total cost is set in the TextView, update the cost1 value
        String cost1 = totalCostTextView.getText().toString();

        // Set the click listener for the payment button
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show the custom dialog
                Dialog customDialog = new Dialog(ViewCart.this);
                customDialog.setContentView(R.layout.activity_shop_custom_dialogbox_payment);

                TextView amt = customDialog.findViewById(R.id.amtpaid);
                amt.setText(cost1);

                // Find the buttons in the dialog layout
                Button buttonOk = customDialog.findViewById(R.id.button_ok);
                Button buttonCancel = customDialog.findViewById(R.id.button_cancel);

                // Set click listeners for the buttons
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle OK button click

                        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Query all documents in the "AddToCart" collection

                        db.collection("AddToCart")
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        // Iterate through the documents and delete them one by one
                                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                            db.collection("AddToCart")
                                                    .document(document.getId())
                                                    .delete()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Document was successfully deleted
                                                            // You can handle success here, such as showing a confirmation message
                                                            Toast.makeText(ViewCart.this, "Amount paid successfully", Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Handle the failure, such as showing an error message
                                                            Toast.makeText(ViewCart.this, "Failed to delete products", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle the failure, such as showing an error message
                                        Toast.makeText(ViewCart.this, "Failed to retrieve products from the cart", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        customDialog.dismiss(); // Close the dialog
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Cancel button click

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference docRef = db.collection("AddToCart").document();

                        docRef.delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Document was successfully deleted
                                        // You can handle success here, such as showing a confirmation message
                                        Toast.makeText(ViewCart.this, "Payment Successful!!", Toast.LENGTH_SHORT).show();
                                        // Finish the activity to return to the cart view
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle the failure, such as showing an error message
                                        Toast.makeText(ViewCart.this, "Failed to delete product", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        customDialog.dismiss(); // Close the dialog
                    }
                });

                customDialog.show();
            }
        });
    }



    private double calculateTotalCost(ArrayList<MainModel> list) {

        double totalCost = 0.0;
        for (MainModel item : list) {
            String costString = item.getCost();
            try {
                double itemCost = Double.parseDouble(costString);
                totalCost += itemCost;
            } catch (NumberFormatException e) {
                // Handle the case where the cost string is not a valid double
                // You can provide a default value or log an error
            }

        }

        return totalCost;
    }
}