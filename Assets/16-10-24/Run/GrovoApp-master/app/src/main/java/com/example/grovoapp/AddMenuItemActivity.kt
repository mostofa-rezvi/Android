package com.example.grovoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grovoapp.databinding.ActivityAddMenuItemBinding

class AddMenuItemActivity : AppCompatActivity() {
    private val binding: ActivityAddMenuItemBinding by lazy {
        ActivityAddMenuItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goBack.setOnClickListener{
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }

        binding.uploadButton.setOnClickListener{
            pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.addFoodButton.setOnClickListener{
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }

    }

    val pickimage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {uri ->
            if (uri != null){
                binding.addFoodImage.setImageURI(uri)
            }
        }
}