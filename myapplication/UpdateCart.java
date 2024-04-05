package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateCart extends AppCompatActivity {

    private String itemId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_item);

        // Find the "Edit" button by its ID
        FirebaseFirestore db;
        ImageButton editButton = findViewById(R.id.edit);
        ImageButton deleteButton = findViewById(R.id.del);
        String docId = getIntent().getStringExtra("docId");
        String currentQuantity = getIntent().getStringExtra("currentQuantity");

        EditText qtyEditText = findViewById(R.id.p_qty);
        qtyEditText.setText(currentQuantity);

// Set an OnClickListener for the "Edit" button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming you have the document ID stored in a variable named 'docId'

// Get the new quantity from the EditText
                String newQuantity = qtyEditText.getText().toString();


                // Update the quantity in the Firestore database for the specific document (docId)
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("AddToCart").document(docId);

                // Create a map with the updated quantity
                Map<String, Object> updates = new HashMap<>();
                updates.put("p_qty", newQuantity);


                // Update the specific document with the new quantity
                docRef.update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Document was successfully updated
                                // You can handle success here, such as showing a confirmation message
                                Toast.makeText(UpdateCart.this, "Quantity updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the failure, such as showing an error message
                                Toast.makeText(UpdateCart.this, "Failed to update quantity", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the specific document (product) from the Firestore database
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("AddToCart").document(docId);

                docRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Document was successfully deleted
                                // You can handle success here, such as showing a confirmation message
                                Toast.makeText(UpdateCart.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                                // Finish the activity to return to the cart view
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the failure, such as showing an error message
                                Toast.makeText(UpdateCart.this, "Failed to delete product", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}