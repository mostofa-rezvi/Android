package com.example.foodzilla3.adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.R
import com.example.foodzilla3.model.restaurant.Item
import com.example.foodzilla3.model.restaurant.MenuType

class ItemListAdaptor(
    private val menu: MenuType,
    private val onDataChangeListener: OnDataChangeListener

) : RecyclerView.Adapter<ItemListAdaptor.itemListViewHolder>() {
    interface OnDataChangeListener {
        fun onDataChanged()
    }

    inner class itemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName: TextView = itemView.findViewById(R.id.txtItemName)
        private var itemPrice: TextView = itemView.findViewById(R.id.txtItemPrice)
        private var itemRating: TextView = itemView.findViewById(R.id.txtRating2)
        private var itemNoOfItems: TextView = itemView.findViewById(R.id.txtNoOfItems)
        private var itemAdder: ImageView = itemView.findViewById(R.id.imgAddItem)
        private var itemRemover: ImageView = itemView.findViewById(R.id.imgRemoveItem)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = (item.price.toInt() * 10).toString()
            itemRating.text = item.rating
        if (item.noOrder==0){
              itemRemover.isClickable=false
            }
            itemNoOfItems.text=item.noOrder.toString()
            itemAdder.setOnClickListener {
                if (item.noOrder < 10) {
                    item.noOrder++
                } else {
                    Toast.makeText(
                        itemView.context,
                        "You had Reached maximum limit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                notifyDataSetChanged()
                onDataChangeListener.onDataChanged()
            }
            itemRemover.setOnClickListener {
                if(item.noOrder>0){
                    item.noOrder--
                }
                notifyDataSetChanged()
                onDataChangeListener.onDataChanged()

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_single, parent, false)
        return itemListViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("Layout called", "${menu.itemsList.size}")

        return menu.itemsList.size
    }

    override fun onBindViewHolder(holder: itemListViewHolder, position: Int) {
        val itemPass = menu.itemsList[position]
        holder.bind(itemPass)

    }
}