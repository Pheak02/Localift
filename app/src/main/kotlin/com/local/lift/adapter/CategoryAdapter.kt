package com.local.lift.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.local.lift.model.CategoryProductHome
import com.local.locallift.R
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categories: List<CategoryProductHome>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.category_image)
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.categoryName
        Log.d("Category", "Binding category: ${category.categoryName}, Image URL: ${category.categoryImage}")
        Picasso.get().load(category.categoryImage).into(holder.categoryImage) // Use Picasso for image loading
    }

    override fun getItemCount(): Int = categories.size
}