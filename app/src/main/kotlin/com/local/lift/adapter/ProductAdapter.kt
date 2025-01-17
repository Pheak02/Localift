package com.local.lift.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.local.lift.model.ProductDetailHome
import com.local.locallift.R
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<ProductDetailHome>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        Log.d("Product", "Binding product: ${product.productName}, Price: $${product.productPrice}, Image URL: ${product.productImage}")
        holder.productName.text = product.productName
        holder.productPrice.text = "$${product.productPrice}"
        Picasso.get().load(product.productImage).into(holder.productImage) // Use Picasso for image loading
    }

    override fun getItemCount(): Int = products.size
}