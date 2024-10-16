package com.example.foodzilla3.adaptor

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.DB.DBHelper
import com.example.foodzilla3.R
import com.example.foodzilla3.SelectedRestaurant.restaurant
import com.example.foodzilla3.model.restaurant.Resturant

class FavouritesListAdaptor(
   private var resturants: ArrayList<Resturant>
) :RecyclerView.Adapter<FavouritesListAdaptor.FavouriteListViewHolder>(){
    inner class FavouriteListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    private var name:TextView=itemView.findViewById(R.id.txtResturantNameInFav)
        private var rating:TextView=itemView.findViewById(R.id.txtRatingInFav)
        private var icFavourate:ImageView=itemView.findViewById(R.id.imgFavourateonInFav)

        fun bind(resturant: Resturant){
            println("Printing resturnt :"+resturant.resturantName)
            name.text=resturant.resturantName
            rating.text=resturant.resRating

            val isFavorite = isRestaurantFavorite(resturant)

            if (isFavorite) {
                icFavourate.setImageResource(R.drawable.ic_favourate_on)

            } else {
                icFavourate.setImageResource(R.drawable.ic_favourate_off)

            }
            icFavourate.setOnClickListener {
                if (isFavorite) {
                    removeFavorite(resturant)
                    icFavourate.setImageResource(R.drawable.ic_favourate_off)
                    notifyItemChanged(bindingAdapterPosition)


                } else {
                    addFavorite(resturant)
                    icFavourate.setImageResource(R.drawable.ic_favourate_on)
                    notifyItemChanged(bindingAdapterPosition)

                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.favourite_recyclerview_single,parent,false)
  return FavouriteListViewHolder(view)
    }

    override fun getItemCount(): Int {
return resturants.size
    }

    override fun onBindViewHolder(holder: FavouriteListViewHolder, position: Int) {
    val resturant=resturants[position]
    holder.bind(resturant)
    }

}