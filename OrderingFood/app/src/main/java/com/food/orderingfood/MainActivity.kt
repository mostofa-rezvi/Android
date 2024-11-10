package com.food.orderingfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.food.orderingfood.Fragment.Notification_Botton_Fragment
import com.food.orderingfood.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var NavController = findNavController(R.id.fragmentContainerView)
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomnav.setupWithNavController(NavController)

        binding.notificationBell.setOnClickListener {
            val bottomSheetDialog = Notification_Botton_Fragment()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
    }
}