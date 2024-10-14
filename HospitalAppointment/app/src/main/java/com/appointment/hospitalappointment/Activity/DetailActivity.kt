package com.appointment.hospitalappointment.Activity

import android.content.Intent
import android.media.RouteListingPreference.Item
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appointment.hospitalappointment.Domain.DoctorsModel
import com.appointment.hospitalappointment.R
import com.appointment.hospitalappointment.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: DoctorsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()

    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.apply {
            titleDoc.text = item.Name
            specialDoc.text = item.Special
            experienceText.text = item.Experience.toString() + " Years"
            patientsText.text = item.Patient
            bioText.text = item.Biography
            locationDoc.text = item.Location

            ratingText.text = "${item.Rating}"

            btnBack.setOnClickListener{finish()}
            btnWebsite.setOnClickListener{
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(item.Site))
                startActivity(i)
            }
            btnMessage.setOnClickListener {
                val uri = Uri.parse("smsto:${item.Mobile}")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("sms_body", "Hi, I am interested in your appointment")
                startActivity(intent)
            }
            btnCall.setOnClickListener{
                val uri = "tel: " + item.Mobile.trim()
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                startActivity(intent)
            }
            btnDirection.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.Location))
                startActivity(intent)
            }
            btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                intent.putExtra(Intent.EXTRA_TEXT, item.Name + " " + item.Location + " " + item.Mobile)
                startActivity(Intent.createChooser(intent, "Share via"))
            }

            Glide.with(this@DetailActivity)
            .load(item.Picture)
            .into(imageDoc)
        }

    }
}