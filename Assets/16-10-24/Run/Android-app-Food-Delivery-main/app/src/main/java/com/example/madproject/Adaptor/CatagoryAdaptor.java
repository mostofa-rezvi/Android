package com.example.madproject.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.madproject.R;
import com.example.madproject.activity.Domain.CatagoryDomain;

import java.util.ArrayList;

public class CatagoryAdaptor extends RecyclerView.Adapter<CatagoryAdaptor.ViewHolder> {
    private ArrayList<CatagoryDomain> catagoryDomains;

    public CatagoryAdaptor(ArrayList<CatagoryDomain> catagoryDomains) {
        this.catagoryDomains = catagoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryAdaptor.ViewHolder holder, int position) {
        holder.categoryName.setText(catagoryDomains.get(position).getTitle());
        String picURL = "";
        switch (position) {
            case 0: {
                picURL = "cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 1: {
                picURL = "cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 2: {
                picURL = "cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
                break;
            }
            case 3: {
                picURL = "cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
                break;
            }
            case 4: {
                picURL = "cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background5));
                break;
            }
        }
        int drawableResourcesID = holder.itemView.getContext().getResources().getIdentifier(picURL, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourcesID)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return catagoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.title2);
            categoryPic = itemView.findViewById(R.id.Pic); // Fix: Correct variable assignment
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}