package com.example.grovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grovoapp.adapter.OrderAdapter
import com.example.grovoapp.databinding.ActivityShowOrdersBinding
import com.example.grovoapp.databinding.ItemOrderBinding

class ShowOrdersActivity : AppCompatActivity() {
    private val binding: ItemOrderBinding by lazy {
        ItemOrderBinding.inflate(layoutInflater)
    }

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_orders)

        ordersRecyclerView = findViewById(R.id.ordersRecyclerView)

        val orderNames = arrayListOf(
            "Fresh salad", "Macarons", "Cheeseburger", "Nutella pancakes",
            "Fresh salad", "Macarons", "Cheeseburger", "Nutella pancakes"
        )
        val orderQuantities = arrayListOf(
            "Quantity: 1", "Quantity: 2", "Quantity: 3", "Quantity: 1",
            "Quantity: 2", "Quantity: 7", "Quantity: 4", "Quantity: 3"
        )
        val orderPrices = arrayListOf(
            "$5", "$10", "$8", "$6",
            "$10", "$5", "$16", "$18"
        )
        val orderImages = arrayListOf(
            R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4,
            R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4
        )


        orderAdapter = OrderAdapter(orderNames, orderQuantities, orderPrices, orderImages)
        ordersRecyclerView.layoutManager = LinearLayoutManager(this)
        ordersRecyclerView.adapter = orderAdapter

        findViewById<ImageButton>(R.id.goBack).setOnClickListener {
            finish()
        }
    }
}
