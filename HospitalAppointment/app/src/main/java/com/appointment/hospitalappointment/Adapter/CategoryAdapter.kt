package com.appointment.hospitalappointment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appointment.hospitalappointment.Domain.CategoryModel
import com.appointment.hospitalappointment.databinding.ViewholderCategoryBinding
import com.bumptech.glide.Glide

class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.Name

        Glide.with(context)
            .load(item.Picture)
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int = items.size
}