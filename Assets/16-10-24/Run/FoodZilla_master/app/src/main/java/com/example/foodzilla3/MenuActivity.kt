package com.example.foodzilla3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodzilla3.adaptor.MenuListAdaptor
import com.example.foodzilla3.model.restaurant.Resturant
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object OrderFromRestaurant {
    var restaurantOrder: Resturant? = null
}
class MenuActivity : AppCompatActivity(),MenuListAdaptor.OnDataChangeListener {
    val selectedRestaurant = SelectedRestaurant.restaurant

    lateinit var rvMenu:RecyclerView
    lateinit var btnCheckout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val toolbar: Toolbar = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar)
        toolbar.title="Menu"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title ="Menu"
        val resturant=selectedRestaurant
        btnCheckout=findViewById(R.id.btnCheckout)
        if(pricer()==0){
            btnCheckout.text="Checkout"
            btnCheckout.isEnabled=false
        }
        else{
            btnCheckout.text="Checkout = "+"\u20B9"+pricer().toString()
            btnCheckout.isEnabled=true
        }
        rvMenu=findViewById(R.id.rvMenu)


        val adaptor = resturant?.let { MenuListAdaptor(resturant = it,this) }
        rvMenu.adapter=adaptor
        rvMenu.layoutManager=LinearLayoutManager(this)

        btnCheckout.setOnClickListener {
            startActivity(Intent(this,ConfirmOrderActivity::class.java))
            OrderFromRestaurant.restaurantOrder = resturant
        }

    }

    override fun onPause() {
        super.onPause()
        GlobalScope.launch {
            delay(1000)
            finish()
        }
    }
    override fun finish() {
        super.finish()
        for (menu in selectedRestaurant?.menuList!!){
            for (item in menu.itemsList){
                item.noOrder=0
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        for (menu in selectedRestaurant?.menuList!!){
            for (item in menu.itemsList){
                item.noOrder=0
            }
        }
        return true
    }

    fun pricer():Int{
        var price=0
        for (menu in selectedRestaurant?.menuList!!){
            for (item in menu.itemsList){
                price += item.noOrder*(item.price.toInt())*10
            }
        }
        return price
    }


    override fun onDataChanged() {
        if(pricer()==0){
            btnCheckout.text="Checkout"
            btnCheckout.isEnabled=false
        }
        else{
            btnCheckout.text="Checkout = "+"\u20B9"+pricer().toString()
            btnCheckout.isEnabled=true
        }
    }
}