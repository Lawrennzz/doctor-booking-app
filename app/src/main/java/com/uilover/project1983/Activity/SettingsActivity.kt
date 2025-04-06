package com.uilover.project1983.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uilover.project1983.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize your settings UI here
    }
}
