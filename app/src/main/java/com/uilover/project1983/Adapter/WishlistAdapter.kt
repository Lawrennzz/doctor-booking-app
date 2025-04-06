package com.uilover.project1983.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project1983.Model.Doctor
import com.uilover.project1983.databinding.ItemWishlistBinding

class WishlistAdapter(
    private val doctors: List<Doctor>
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(private val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(doctor: Doctor) {
            binding.apply {
                doctorName.text = doctor.name
                specialty.text = doctor.specialty
                experience.text = doctor.experience
                rating.text = doctor.rating
                
                // Set up the favorite button
                favoriteButton.setOnClickListener {
                    // TODO: Implement remove from wishlist functionality
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(doctors[position])
    }

    override fun getItemCount() = doctors.size
}
