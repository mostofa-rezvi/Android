package com.food.orderingfood

import android.R.layout.simple_dropdown_item_1line
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.food.orderingfood.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    private val binding: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val locationList = arrayOf("Azimpur", "Dhanmondi", "Khilgoan", "Mirpur", "Banani")

        binding.listOfLocations.setOnClickListener{
            binding.listOfLocations.showDropDown()
        }

        val adapter = ArrayAdapter(this, simple_dropdown_item_1line, locationList)
        val autoCompleteTextView = binding.listOfLocations
        autoCompleteTextView.setAdapter(adapter)

        binding.submitButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}