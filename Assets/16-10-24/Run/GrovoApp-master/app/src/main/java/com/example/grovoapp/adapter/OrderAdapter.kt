package com.example.grovoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grovoapp.databinding.ItemOrderBinding

class OrderAdapter(
    private val orderNames: MutableList<String>,
    private val orderQuantities: ArrayList<String>,
    private val orderPrices: MutableList<String>,
    private val orderImages: MutableList<Int>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = orderNames.size

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                foodName.text = orderNames[position]
                foodPrice.text = orderPrices[position]
                foodQuantity.text = orderQuantities[position]
                foodImage.setImageResource(orderImages[position])

                removeButton.setOnClickListener {
                    removeItem(position)
                }
            }
        }
    }

    private fun removeItem(position: Int) {
        orderNames.removeAt(position)
        orderQuantities.removeAt(position)
        orderPrices.removeAt(position)
        orderImages.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }
}
