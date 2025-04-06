package com.uilover.project1983.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uilover.project1983.Adapter.ThemeAdapter
import com.uilover.project1983.Model.ThemeItem
import com.uilover.project1983.R
import com.uilover.project1983.databinding.ActivityThemeSettingsBinding

class ThemeSettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeSettingsBinding
    private lateinit var adapter: ThemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityThemeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val themeList = listOf(
            ThemeItem(
                title = "Light",
                primaryColor = R.color.app_primary_500,
                secondaryColor = R.color.app_secondary_500,
                textColor = R.color.app_onPrimary
            ),
            ThemeItem(
                title = "Dark",
                primaryColor = R.color.app_dark_background,
                secondaryColor = R.color.app_dark_surface,
                textColor = R.color.app_white
            ),
            ThemeItem(
                title = "Purple",
                primaryColor = R.color.app_purple_500,
                secondaryColor = R.color.app_purple_100,
                textColor = R.color.app_white
            ),
            ThemeItem(
                title = "Blue",
                primaryColor = R.color.app_blue,
                secondaryColor = R.color.app_green,
                textColor = R.color.app_white
            )
        )

        adapter = ThemeAdapter(themeList)
        binding.themeList.apply {
            layoutManager = LinearLayoutManager(this@ThemeSettingsActivity)
            adapter = this@ThemeSettingsActivity.adapter
        }
    }
}