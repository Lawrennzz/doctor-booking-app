package com.uilover.project1983.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project1983.Model.ThemeItem
import com.uilover.project1983.R
import com.uilover.project1983.Activity.MainActivity
import com.uilover.project1983.databinding.ItemThemeBinding

class ThemeAdapter(
    private val themeList: List<ThemeItem>
) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

    class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemThemeBinding.bind(itemView)

        fun bind(item: ThemeItem, context: Context) {
            binding.titleTxt.text = item.title
            
            // Apply theme colors to preview
            binding.previewTitle.setTextColor(itemView.context.getColor(item.primaryColor))
            binding.previewText.setTextColor(itemView.context.getColor(item.textColor))
            
            // Set background color for preview
            binding.previewContainer.setBackgroundColor(itemView.context.getColor(item.secondaryColor))
            
            // Handle click to apply theme
            binding.root.setOnClickListener {
                when (item.title) {
                    "Light" -> {
                        context.setTheme(R.style.Theme_Project1983_Light)
                    }
                    "Dark" -> {
                        context.setTheme(R.style.Theme_Project1983_Dark)
                    }
                    "Purple" -> {
                        context.setTheme(R.style.Theme_Project1983_Purple)
                    }
                    "Blue" -> {
                        context.setTheme(R.style.Theme_Project1983_Blue)
                    }
                }
                // Restart the activity to apply the theme
                context.startActivity(Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent, false)
        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(themeList[position], holder.itemView.context)
    }

    override fun getItemCount() = themeList.size
}
