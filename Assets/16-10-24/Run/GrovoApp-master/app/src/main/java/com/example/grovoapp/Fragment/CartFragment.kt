package com.example.grovoapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grovoapp.CongratsBottomSheet
import com.example.grovoapp.PayoutActivity
import com.example.grovoapp.R
import com.example.grovoapp.adapter.CartAdapter
import com.example.grovoapp.adapter.PopularAdapter
import com.example.grovoapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val cartFoodName = listOf("Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger")
        val cartItemPrice = listOf("$7", "$9", "$5", "$13")
        val cartImage = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)
        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(cartItemPrice), ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter
        binding.proceedButton.setOnClickListener{
            val intent = Intent(requireContext(), PayoutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}