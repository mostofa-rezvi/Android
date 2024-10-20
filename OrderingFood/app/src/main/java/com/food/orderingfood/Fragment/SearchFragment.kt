package com.food.orderingfood.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.food.orderingfood.R
import com.food.orderingfood.adapter.MenuAdapter
import com.food.orderingfood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    private val originalMenuFoodName = listOf("Burger", "Sandwich", "Momo", "Fresh Salad", "Cheeseburger", "Pizza", "Sandwich Burger", "Burger Sandwich", "Cheese Burger", "Freshsalad")
    private val originalMenuItemPrice = listOf("$7", "$9", "$5", "$13", "$7", "$9", "$5", "$13", "$9", "6", "15", "12")
    private val originalMenuImage = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4,R.drawable.menu5, R.drawable.menu6, R.drawable.menu1, R.drawable.menu7, R.drawable.menu2, R.drawable.menu3, R.drawable.menu5, R.drawable.menu3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = MenuAdapter(filteredMenuFoodName, filteredMenuItemPrice, filteredMenuImage, requireContext())
        binding.menuRecyclerViewSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerViewSearch.adapter = adapter


        setupSearchView()


        showAllMenuItems()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAllMenuItems() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    @SuppressLint("NotifyDataSetChanged")
    private fun filterMenuItems(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        originalMenuFoodName.forEachIndexed{index, foodName ->
            if (foodName.contains(query, ignoreCase = true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(originalMenuItemPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }
}