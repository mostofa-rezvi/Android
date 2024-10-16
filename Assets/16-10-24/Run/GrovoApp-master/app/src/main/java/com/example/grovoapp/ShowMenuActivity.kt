package com.example.grovoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grovoapp.adapter.ShowMenuAdapter
import com.example.grovoapp.databinding.ActivityShowMenuBinding

class ShowMenuActivity : AppCompatActivity() {
    private val binding: ActivityShowMenuBinding by lazy {
        ActivityShowMenuBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }

        val menuFoodName = listOf("Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger")
        val menuPrice = listOf("$7", "$9", "$5", "$13")
        val menuFoodImage = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)

        val adapter = ShowMenuAdapter(ArrayList(menuFoodName), ArrayList(menuPrice), ArrayList(menuFoodImage))
        binding.showMenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.showMenuRecyclerView.adapter = adapter
    }
}
