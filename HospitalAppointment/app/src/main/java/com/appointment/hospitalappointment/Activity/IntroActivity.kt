package com.appointment.hospitalappointment.Activity

import android.content.Intent
import android.os.Bundle
import com.appointment.hospitalappointment.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener{
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            }
        }
    }
}