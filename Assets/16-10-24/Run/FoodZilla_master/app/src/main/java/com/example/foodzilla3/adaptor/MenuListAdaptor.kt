package com.example.foodzilla3.adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.R
import com.example.foodzilla3.model.restaurant.Item
import com.example.foodzilla3.model.restaurant.MenuType
import com.example.foodzilla3.model.restaurant.Resturant
import java.util.Locale

class MenuListAdaptor(
    private var resturant: Resturant,
    private val onDataChangeListener: OnDataChangeListener

) :RecyclerView.Adapter<MenuListAdaptor.menuListViewHolder>(){
    interface OnDataChangeListener {
        fun onDataChanged()
    }

    inner class menuListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
private var menuName:TextView=itemView.findViewById(R.id.txtmenuTitle)
        private var rvItem:RecyclerView=itemView.findViewById(R.id.rvItems)

        fun bind(menuType:MenuType){

            menuName.text= menuType.menuTypeName.lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }


            val adaptor2=ItemListAdaptor(menuType,object :ItemListAdaptor.OnDataChangeListener{
                override fun onDataChanged() {
                    onDataChangeListener.onDataChanged()
                }
            })
            rvItem.adapter=adaptor2
            menuType.displayMenu()
            rvItem.layoutManager=LinearLayoutManager(itemView.context)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): menuListViewHolder {
        var view=LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_recyclerview_single,parent,false)

        return menuListViewHolder(view)
    }

    override fun getItemCount(): Int {
return resturant.menuList.size
    }

    override fun onBindViewHolder(holder: menuListViewHolder, position: Int) {

val menuPass=resturant.menuList[position]
        resturant.displayRes()
        Log.d("Layout called 1","${menuPass.itemsList.size}")

        holder.bind(menuPass)
    }

}