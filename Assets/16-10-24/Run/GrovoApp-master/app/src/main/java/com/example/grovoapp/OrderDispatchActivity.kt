package com.example.grovoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grovoapp.databinding.ActivityAddMenuItemBinding
import com.example.grovoapp.databinding.ActivityOrderDispatchBinding

class OrderDispatchActivity : AppCompatActivity() {
    private val binding: ActivityOrderDispatchBinding by lazy {
        ActivityOrderDispatchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBack.setOnClickListener{
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }
    }
}