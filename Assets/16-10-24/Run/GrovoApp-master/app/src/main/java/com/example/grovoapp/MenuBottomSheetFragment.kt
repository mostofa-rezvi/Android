package com.example.grovoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grovoapp.adapter.MenuAdapter
import com.example.grovoapp.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener{
            dismiss()
        }
        val menuFoodName = listOf("Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger", "Nutella pancakes", "Fresh salad", "Macarons", "Cheeseburger")
        val menuItemPrice = listOf("$7", "$9", "$5", "$13", "$7", "$9", "$5", "$13")
        val menuImage = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4,R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)

        val adapter = MenuAdapter(ArrayList(menuFoodName), ArrayList(menuItemPrice), ArrayList(menuImage), requireContext())

        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {

    }
}