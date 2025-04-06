package com.uilover.project1983.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uilover.project1983.Adapter.WishlistAdapter
import com.uilover.project1983.Model.Doctor
import com.uilover.project1983.databinding.ActivityWishlistBinding
import com.uilover.project1983.R

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WishlistActivity)
            adapter = WishlistAdapter(getSampleWishlist())
        }

        // Show empty state if no items
        updateEmptyState()
    }

    private fun getSampleWishlist(): List<Doctor> {
        return listOf(
            Doctor(
                "Dr. Emily Johnson",
                "Dermatologist",
                "5 years experience",
                "4.8",
                R.drawable.women
            ),
            Doctor(
                "Dr. Michael Brown",
                "Cardiologist",
                "8 years experience",
                "4.9",
                R.drawable.women
            )
        )
    }

    private fun updateEmptyState() {
        binding.apply {
            recyclerView.visibility = if (getSampleWishlist().isEmpty()) View.GONE else View.VISIBLE
            emptyState.visibility = if (getSampleWishlist().isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
