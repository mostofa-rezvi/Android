package com.appointment.hospitalappointment.Adapter

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appointment.hospitalappointment.Activity.DetailActivity
import com.appointment.hospitalappointment.Domain.DoctorsModel
import com.appointment.hospitalappointment.databinding.ViewholderTopDoctorBinding
import com.appointment.hospitalappointment.databinding.ViewholderTopDoctorListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class TopDoctorAdapter2(val items:MutableList<DoctorsModel>):
    RecyclerView.Adapter<TopDoctorAdapter2.Viewholder>() {

    private var context: Context?=null

    class Viewholder(val binding: ViewholderTopDoctorListBinding):
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorAdapter2.Viewholder {
        context = parent.context
        val binding = ViewholderTopDoctorListBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: TopDoctorAdapter2.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.nameTextAppointment.text = item.Name
        holder.binding.specialTextAppointment.text = item.Special
        holder.binding.scoreTextAppointment.text = item.Rating.toString()
//        holder.binding.ratingBar.rating = item.Rating.toString()
        holder.binding.degreeText.text = "Professional Doctor"

        Glide.with(holder.itemView.context)
            .load(item.Picture)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.imageAppointment)

        holder.binding.btnMakeAppointment.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}