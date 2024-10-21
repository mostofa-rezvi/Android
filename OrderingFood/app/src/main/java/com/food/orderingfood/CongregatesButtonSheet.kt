package com.food.orderingfood

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.food.orderingfood.databinding.FragmentCongregatesButtonSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CongregatesButtonSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCongregatesButtonSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCongregatesButtonSheetBinding.inflate(layoutInflater, container, false)
        binding.goHomeButton.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}