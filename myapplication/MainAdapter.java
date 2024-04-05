package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    Context context;
    ArrayList<MainModel> list;

    public MainAdapter(Context context,ArrayList<MainModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.main_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {

        MainModel mainModel = list.get(position);
        holder.p_name.setText(mainModel.getP_name()); // Set the product name
        holder.p_qty.setText(mainModel.getP_qty());
        holder.cost.setText(mainModel.getCost());

        // Set an OnClickListener for the "Edit" button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Edit" button click here
                String docId = mainModel.getItemId();
                String currentQuantity = mainModel.getP_qty();

                // Start the UpdateCart activity and pass the docId and currentQuantity
                // Pass the docId to the UpdateCart activity to edit the specific product

                // Start the UpdateCart activity and pass the docId and currentQuantity
                Intent intent = new Intent(context, UpdateCart.class);
                intent.putExtra("docId", docId);
                intent.putExtra("currentQuantity", currentQuantity);
                context.startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Edit" button click here
                String docId = mainModel.getItemId();
                String currentQuantity = mainModel.getP_qty();

                // Start the UpdateCart activity and pass the docId and currentQuantity
                // Pass the docId to the UpdateCart activity to edit the specific product

                // Start the UpdateCart activity and pass the docId and currentQuantity
                Intent intent = new Intent(context, UpdateCart.class);
                intent.putExtra("docId", docId);
                // intent.putExtra("currentQuantity", currentQuantity);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageButton editButton;
        public ImageButton deleteButton;
        TextView p_name;
        EditText p_qty;
        TextView cost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            editButton=itemView.findViewById(R.id.edit);
            deleteButton=itemView.findViewById(R.id.del);
            p_name=itemView.findViewById(R.id.p_name);
            p_qty=itemView.findViewById(R.id.p_qty);
            cost=itemView.findViewById(R.id.cost);
        }
    }
}