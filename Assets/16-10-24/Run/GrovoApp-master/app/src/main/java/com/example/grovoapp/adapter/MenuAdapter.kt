package com.example.grovoapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grovoapp.DetailsActivity
import com.example.grovoapp.databinding.MenuItemBinding

class MenuAdapter (private val menuItemsName:MutableList<String>, private val menuItemPrice:MutableList<String>, private val menuImage:MutableList<Int>, private val requireContext: Context) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val itemClickListener: OnClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemsName.size

    inner class MenuViewHolder(private val binding: MenuItemBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    itemClickListener?.onItemClick(position)
                    //details

                    val intent = Intent(requireContext, DetailsActivity::class.java)
                    intent.putExtra("MenuItemName", menuItemsName.get(position))
                    intent.putExtra("MenuItemImage", menuImage.get(position))
                    requireContext.startActivity(intent)
                }
            }
        }
        fun bind(position: Int){
            binding.apply{
                menuFoodName.text = menuItemsName[position]
                menuPrice.text = menuItemPrice[position]
                menuFoodImage.setImageResource(menuImage[position])
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(position:Int)
    }
}
