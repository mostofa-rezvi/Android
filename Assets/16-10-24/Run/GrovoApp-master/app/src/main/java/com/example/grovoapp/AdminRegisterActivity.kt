package com.example.grovoapp

import android.R.layout.simple_dropdown_item_1line
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grovoapp.databinding.ActivityAdminRegisterBinding

class AdminRegisterActivity : AppCompatActivity() {
    private val binding: ActivityAdminRegisterBinding by lazy {
        ActivityAdminRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val locationList = arrayOf("Novi Sad", "Beograd", "Zrenjanin")

        binding.listOfLocations.setOnClickListener{
            binding.listOfLocations.showDropDown()
        }

        binding.alreadyhavebutton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerRestarauntButton.setOnClickListener{
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }

        val adapter = ArrayAdapter(this, simple_dropdown_item_1line, locationList)
        val autoCompleteTextView = binding.listOfLocations
        autoCompleteTextView.setAdapter(adapter)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}