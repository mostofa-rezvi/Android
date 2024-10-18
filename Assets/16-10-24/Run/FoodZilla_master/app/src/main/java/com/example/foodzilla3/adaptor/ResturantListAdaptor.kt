package com.example.foodzilla3.adaptor

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodzilla3.DB.DBHelper
import com.example.foodzilla3.R
import com.example.foodzilla3.databinding.ActivityMainBinding
import com.example.foodzilla3.model.restaurant.Resturant

class ResturantListAdaptor(
    private var resturants: ArrayList<Resturant>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ResturantListAdaptor.resturantListViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(restaurant: Resturant)

    }
    inner class resturantListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name:TextView=itemView.findViewById(R.id.txtResturantName)
        private var rating:TextView=itemView.findViewById(R.id.txtRating)
        private var icFavourate:ImageView=itemView.findViewById(R.id.imgFavourateon)


        fun bind(restaurant: Resturant) {
            name.text = restaurant.resturantName
            rating.text = restaurant.resRating

            val isFavorite = isRestaurantFavorite(restaurant)
            if (isFavorite) {
                icFavourate.setImageResource(R.drawable.ic_favourate_on)

            } else {
                icFavourate.setImageResource(R.drawable.ic_favourate_off)

            }
            icFavourate.setOnClickListener {
                if (isFavorite) {
                    removeFavorite(restaurant)
                    icFavourate.setImageResource(R.drawable.ic_favourate_off)
                    notifyItemChanged(bindingAdapterPosition)


                } else {
                    addFavorite(restaurant)
                    icFavourate.setImageResource(R.drawable.ic_favourate_on)
                    notifyItemChanged(bindingAdapterPosition)

                }
            }
            itemView.setOnClickListener {
                listener.onItemClick(restaurant)
            }
        }


        private fun isRestaurantFavorite(restaurant: Resturant): Boolean {
            val dbHelper = DBHelper(itemView.context)
            val db = dbHelper.readableDatabase
            val selection = "${DBHelper.COLUMN_NAME} = ?"
            val selectionArgs = arrayOf(restaurant.resturantName)
            val cursor = db.query(
                DBHelper.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            val isFavorite = cursor.count > 0
            cursor.close()
            db.close()
            return isFavorite
        }

        private fun addFavorite(restaurant: Resturant) {
            val dbHelper = DBHelper(itemView.context)
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put(DBHelper.COLUMN_NAME, restaurant.resturantName)
            values.put(DBHelper.COLUMN_RATING, restaurant.resRating)
            db.insertWithOnConflict(
                DBHelper.TABLE_NAME,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE
            )
            db.close()
        }

        private fun removeFavorite(restaurant: Resturant) {
            val dbHelper = DBHelper(itemView.context)
            val db = dbHelper.writableDatabase
            val selection = "${DBHelper.COLUMN_NAME} = ?"
            val selectionArgs = arrayOf(restaurant.resturantName)
            db.delete(DBHelper.TABLE_NAME, selection, selectionArgs)
            db.close()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): resturantListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.resturant_recyclerview_single, parent, false)

        return resturantListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resturants.size
    }

    override fun onBindViewHolder(holder: resturantListViewHolder, position: Int) {
        val resturant=resturants[position]
        holder.bind(resturant)
    }
}

