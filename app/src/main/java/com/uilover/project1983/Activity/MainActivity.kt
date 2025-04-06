package com.uilover.project1983.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.uilover.project1983.Adapter.CategoryAdapter
import com.uilover.project1983.Adapter.TopDoctorAdapter
import com.uilover.project1983.ViewModel.MainViewModel
import com.uilover.project1983.databinding.ActivityMainBinding
import com.uilover.project1983.R

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up search functionality
        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterDoctors(s.toString())
            }
            
            override fun afterTextChanged(s: Editable?) {}
        })

        initCategory()
        initTopDoctors()

        // Settings button click listener (goes to ThemeSettingsActivity)
        binding.settingsBtn.setOnClickListener {
            startActivity(Intent(this, ThemeSettingsActivity::class.java))
        }

        // Bell icon click listener
       //binding.imageView3.setOnClickListener {
           // val intent = Intent(this, AppointmentNotificationActivity::class.java)
            //startActivity(intent)
       //}
    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctor.visibility = View.VISIBLE
            viewModel.filteredDoctors.observe(this@MainActivity, Observer { doctors ->
                recyclerViewTopDoctor.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewTopDoctor.adapter = TopDoctorAdapter(doctors)
                progressBarTopDoctor.visibility = View.GONE
            })
            viewModel.loadDoctors()

            doctorListTxt.setOnClickListener {
                startActivity(Intent(this@MainActivity, TopDoctorsActivity::class.java))
            }

            // Wishlist button click listener
            wishlistBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, WishlistActivity::class.java))
            }
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    fun onExplorerClick(view: View) {
        startActivity(Intent(this, TopDoctorsActivity::class.java))
    }
}