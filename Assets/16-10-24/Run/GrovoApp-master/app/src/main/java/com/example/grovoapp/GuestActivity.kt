package com.example.grovoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grovoapp.adapter.MenuAdapter
import com.example.grovoapp.databinding.ActivityGuestBinding

class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding
    private lateinit var adapter: MenuAdapter

    // Originalni podaci za meni
    private val originalMenuFoodName = listOf("Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger", "Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger")
    private val originalMenuItemPrice = listOf("$7", "$9", "$5", "$13", "$7", "$9", "$5", "$13")
    private val originalMenuImage = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)

    // Filtrirani podaci za meni
    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToLogin.setOnClickListener{
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        // Inicijalizacija RecyclerView-a i adaptera
        setupRecyclerView()

        // Podešavanje SearchView-a
        setupSearchView()

        // Prikaži sve stavke iz menija prilikom pokretanja aktivnosti
        showAllMenuItems()
    }

    private fun setupRecyclerView() {
        adapter = MenuAdapter(filteredMenuFoodName, filteredMenuItemPrice, filteredMenuImage, this)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.menuRecyclerView.adapter = adapter
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun showAllMenuItems() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun filterMenuItems(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase = true)) {
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(originalMenuItemPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

}
