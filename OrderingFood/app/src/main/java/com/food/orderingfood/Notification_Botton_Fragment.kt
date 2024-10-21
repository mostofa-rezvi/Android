package com.food.orderingfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.food.orderingfood.adapter.NotificationAdapter
import com.food.orderingfood.databinding.FragmentNotificationBottonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Notification_Botton_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBottonBinding.inflate(layoutInflater, container, false)
        val notifications = listOf("Your order has been canceled successfully!", "Order has been taken by the driver!", "Order placed successfully!")
        val notificationImages = listOf(R.drawable.sademoji, R.drawable.truck, R.drawable.congrats)

        val adapter = NotificationAdapter(
            ArrayList(notifications),
            ArrayList(notificationImages),

            )
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter = adapter

        return binding.root
    }
}