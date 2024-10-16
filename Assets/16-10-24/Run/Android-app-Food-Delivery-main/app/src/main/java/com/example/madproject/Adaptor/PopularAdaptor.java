package com.example.madproject.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madproject.R;
import com.example.madproject.ShowDetailActivity;
import com.example.madproject.activity.Domain.FoodDomain;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> popularFood;

    public PopularAdaptor(ArrayList<FoodDomain> catagoryDomains) {
        this.popularFood = catagoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, int Position) {
        holder.title.setText(popularFood.get(Position).getTitle());
        holder.Fee.setText(String.valueOf(popularFood.get(Position).getFee()));
        String picURL = "";

        int drawableResourcesID = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(Position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourcesID)
                .into(holder.Pic);

        holder.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularFood.get(Position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, Fee;
        ImageView Pic;
        TextView AddBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2);
            Fee = itemView.findViewById(R.id.fee);
            Pic = itemView.findViewById(R.id.Pic);
            AddBtn = itemView.findViewById(R.id.textView4);

        }
    }
}