package com.appointment.hospitalappointment.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appointment.hospitalappointment.Adapter.TopDoctorAdapter
import com.appointment.hospitalappointment.Adapter.TopDoctorAdapter2
import com.appointment.hospitalappointment.R
import com.appointment.hospitalappointment.ViewModel.MainViewModel
import com.appointment.hospitalappointment.databinding.ActivityTopDoctorsBinding

class TopDoctorsActivity : BaseActivity() {

    private lateinit var binding: ActivityTopDoctorsBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopDoctors()
    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctorList.visibility = View.VISIBLE
            viewModel.doctors.observe(this@TopDoctorsActivity, Observer {
                viewTopDoctorList.layoutManager =
                    LinearLayoutManager(this@TopDoctorsActivity, LinearLayoutManager.HORIZONTAL, false)
                viewTopDoctorList.adapter = TopDoctorAdapter2(it)
                progressBarTopDoctorList.visibility = View.GONE
            })
            viewModel.loadDoctors()

            btnBackDoctorList.setOnClickListener {
                finish()
            }
        }
    }
}