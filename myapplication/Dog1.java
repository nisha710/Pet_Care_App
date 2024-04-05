package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Dog1 extends AppCompatActivity {

    ImageView dog;
    ImageButton add,sub;
    TextView cost,name,qty;
    Button addcart;
    FirebaseFirestore db;

    //TextView tx;
    ImageButton prev;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog1);

        db= FirebaseFirestore.getInstance();

        dog=(ImageView) findViewById(R.id.dog1img);
        cost=(TextView) findViewById(R.id.dog1cost);
        name= (TextView) findViewById(R.id.DogFood1);
        addcart=(Button) findViewById(R.id.addcartdog1);
        add=(ImageButton) findViewById(R.id.adddog1);
        sub=(ImageButton) findViewById(R.id.subdog1);
        qty=(TextView) findViewById(R.id.qty);
        prev = (ImageButton) findViewById(R.id.back_arrow);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dog1.this,Shop_Activity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qty1=qty.getText().toString();
                int qty2=Integer.parseInt(qty1);
                qty2++;
                qty.setText(String.valueOf(qty2));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String qty1=qty.getText().toString();
                int qty2=Integer.parseInt(qty1);

                if(qty2>0) qty2--;
                qty.setText(String.valueOf(qty2));

            }
        });

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email="sakshi@gmail.com";
                String p_name=name.getText().toString();
                String p_qty=qty.getText().toString();

                String qty1=qty.getText().toString();
                int qty2=Integer.parseInt(qty1);

                String cost1=cost.getText().toString();
                int cost2=Integer.parseInt(cost1);
                cost2=cost2*qty2;
                cost.setText(String.valueOf(cost2));

                String cost3=cost.getText().toString();

                Map<String,Object> AddToCart =new HashMap<>();
                AddToCart.put("email",email);
                AddToCart.put("p_name",p_name);
                AddToCart.put("p_qty",p_qty);
                AddToCart.put("cost",cost3);

                db.collection("AddToCart")
                        .add(AddToCart)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Dog1.this,"Added To Cart",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Dog1.this,"Not Added",Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

    }
}