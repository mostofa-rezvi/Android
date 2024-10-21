package com.food.orderingfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.food.orderingfood.databinding.ActivityPayOutBinding


class PayOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityPayOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.placeMyOrder.setOnClickListener {
            val bottomSheetDialog = CongregatesButtonSheet()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
    }
}