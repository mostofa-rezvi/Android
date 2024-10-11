package com.appointment.hospitalappointment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appointment.hospitalappointment.Domain.DoctorsModel
import com.appointment.hospitalappointment.databinding.ViewholderTopDoctorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class TopDoctorAdapter(val items:MutableList<DoctorsModel>):
    RecyclerView.Adapter<TopDoctorAdapter.Viewholder>() {

    private var context: Context?=null

    class Viewholder(val binding: ViewholderTopDoctorBinding):
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderTopDoctorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: TopDoctorAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.nameText.text = item.Name
        holder.binding.specialText.text = item.Special
        holder.binding.scoreText.text = item.Rating.toString()
        holder.binding.yearText.text = item.Experience.toString()+ "Year"

        Glide.with(holder.itemView.context)
            .load(item.Picture)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.imgDoctor)
    }

    override fun getItemCount(): Int = items.size
}