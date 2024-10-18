package com.example.foodzilla3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import com.example.foodzilla3.model.restaurant.Item

class ConfirmOrderActivity : AppCompatActivity() {
    val selectedRestaurant = OrderFromRestaurant.restaurantOrder
    lateinit var linearLayout:LinearLayout
    lateinit var orderNowBtn:Button
    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
        toolbar=findViewById(R.id.toolbar4)
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Confirm Order"

        linearLayout=findViewById(R.id.llForOrder)
        orderNowBtn=findViewById(R.id.buttonGTotal)
        var itemList=ArrayList<Item>()
        var grandTotal=0
        for(menu in selectedRestaurant?.menuList!!){
            for (item in menu.itemsList){
                if(item.noOrder!=0){
                    itemList.add(item)
                    grandTotal+=item.noOrder*10*item.price.toInt()
                }
            }
        }
        for(item in itemList){
            val itemView=LayoutInflater.from(this).inflate(R.layout.single_item_order,null)
            val itemname:TextView=itemView.findViewById(R.id.orderItemName)
            val itemDes:TextView=itemView.findViewById(R.id.orderItemNoPrice)
            val itemtot:TextView=itemView.findViewById(R.id.orderItemTotal)
            itemname.text=item.name
            itemDes.text="${item.noOrder.toString()} X \u20B9 ${item.price}0 = "
            itemtot.text="\u20B9 ${(item.noOrder*10*(item.price.toInt())).toString()}"
            linearLayout.addView(itemView)

        }
        orderNowBtn.text="Order now :₹ ${grandTotal}"

        orderNowBtn.setOnClickListener {
            var intent:Intent=Intent(this,PaymentActivity::class.java)
            startActivity(intent)
            intent.putExtra("GRANDTOTAL",grandTotal)
            OrderFromRestaurant.restaurantOrder=null
            finish()
        }

    }

}