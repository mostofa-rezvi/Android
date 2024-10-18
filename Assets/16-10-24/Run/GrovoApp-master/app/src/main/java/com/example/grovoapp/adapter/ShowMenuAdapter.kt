package com.example.grovoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grovoapp.databinding.FooditemBinding

class ShowMenuAdapter(
    private val menuItemName: ArrayList<String>,
    private val menuItemPrice: ArrayList<String>,
    private val menuItemImage: ArrayList<Int>
) : RecyclerView.Adapter<ShowMenuAdapter.AddItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = FooditemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemName.size

    inner class AddItemViewHolder(private val binding: FooditemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                buyAgainFoodName.text = menuItemName[position]
                buyAgainFoodPrice.text = menuItemPrice[position]
                buyAgainFoodImage.setImageResource(menuItemImage[position])

                Remove.setOnClickListener {
                    removeItem(position)
                }
            }
        }
    }

    private fun removeItem(position: Int) {
        menuItemName.removeAt(position)
        menuItemPrice.removeAt(position)
        menuItemImage.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }
}
