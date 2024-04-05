package com.example.myapplication.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> categoriesLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesLocations) {
        this.categoriesLocations = categoriesLocations;
    }

    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        AdapterAllCategoriesViewHolder categoriesViewHolder = new AdapterAllCategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {
        CategoriesHelperClass categoriesHelperClass = categoriesLocations.get(position);

        holder.image.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());
        holder.relativeLayout.setBackground(categoriesHelperClass.getGradient());
    }

    @Override
    public int getItemCount() {

        return categoriesLocations.size();
    }

    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        RelativeLayout relativeLayout;

        public AdapterAllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            relativeLayout =itemView.findViewById(R.id.background_gradient);
            image = itemView.findViewById(R.id.cat_image);
            title = itemView.findViewById(R.id.cat_title);

        }
    }
}
