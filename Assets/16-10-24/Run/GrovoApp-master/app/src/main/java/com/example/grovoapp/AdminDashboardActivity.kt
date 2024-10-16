package com.example.grovoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grovoapp.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {

    private val binding: ActivityAdminDashboardBinding by lazy {
        ActivityAdminDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.addItem.setOnClickListener{
            val intent = Intent(this, AddMenuItemActivity::class.java)
            startActivity(intent)
        }

        binding.showMenu.setOnClickListener{
            val intent = Intent(this, ShowMenuActivity::class.java)
            startActivity(intent)
        }

        binding.dispatch.setOnClickListener{
            val intent = Intent(this, OrderDispatchActivity::class.java)
            startActivity(intent)
        }

        binding.signOut.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.orders.setOnClickListener{
            val intent = Intent(this, ShowOrdersActivity::class.java)
            startActivity(intent)
        }
    }

}