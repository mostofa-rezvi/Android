package com.food.orderingfood.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.food.orderingfood.PayOutActivity
import com.food.orderingfood.R
import com.food.orderingfood.adapter.CartAdapter
import com.food.orderingfood.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater, container, false)

        val cartFoodName = listOf("Burger", "Sandwich", "Momo", "Fresh Salad", "Cheeseburger", "Pizza")
        val cartItemPrice = listOf("$7", "$9", "$5", "$13", "$9", "$19")
        val cartImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu6
        )
        val adapter = CartAdapter(
            ArrayList(cartFoodName),
            ArrayList(cartItemPrice),
            ArrayList(cartImage)
        )

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener{
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}