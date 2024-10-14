package com.travel.travelguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.travel.travelguide.R;
import com.travel.travelguide.databinding.ViewholderCategoryBinding;
import com.travel.travelguide.domain.Category;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {

    private final List<Category> items;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;
    private Context context;

    public CategoryAdapter(List<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(layoutInflater, parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int position) {
        Category item = items.get(holder.getAdapterPosition());
        holder.viewholderCategoryBinding.titleCategory.setText(item.getName());

        Glide.with(holder.itemView.getContext())
                .load(item.getImagePath())
                .into(holder.viewholderCategoryBinding.imageView2);

        holder.viewholderCategoryBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                lastSelectedPosition = selectedPosition;
                selectedPosition = currentPosition;

                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        holder.viewholderCategoryBinding.titleCategory.setTextColor(ContextCompat.getColor(context, R.color.white));

        if (selectedPosition == holder.getAdapterPosition()) {
            holder.viewholderCategoryBinding.imageView2.setBackgroundResource(0);
            holder.viewholderCategoryBinding.mailLayout.setBackgroundResource(R.drawable.blue_bg);
            holder.viewholderCategoryBinding.titleCategory.setVisibility(View.VISIBLE);
        } else {
            holder.viewholderCategoryBinding.imageView2.setBackgroundResource(R.drawable.gray_bg);
            holder.viewholderCategoryBinding.mailLayout.setBackgroundResource(0);
            holder.viewholderCategoryBinding.titleCategory.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private final ViewholderCategoryBinding viewholderCategoryBinding;

        public Viewholder(ViewholderCategoryBinding viewholderCategoryBinding) {
            super(viewholderCategoryBinding.getRoot());
            this.viewholderCategoryBinding = viewholderCategoryBinding;
        }
    }
}
