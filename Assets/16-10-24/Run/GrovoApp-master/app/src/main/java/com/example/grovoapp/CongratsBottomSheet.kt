package com.example.grovoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.DumpableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grovoapp.databinding.FragmentCongratsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CongratsBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCongratsBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = FragmentCongratsBottomSheetBinding.inflate(layoutInflater, container, false)
            binding.goHome.setOnClickListener{
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
            return binding.root
        }
}