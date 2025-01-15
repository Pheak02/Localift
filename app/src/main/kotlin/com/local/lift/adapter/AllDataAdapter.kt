package com.local.lift.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.local.lift.activity.ProductDetailActivity
import com.local.lift.model.ProductDataItem
import com.local.locallift.databinding.ViewHolderAllDataBinding
import com.squareup.picasso.Picasso

class AllDataAdapter:ListAdapter<ProductDataItem,AllDataAdapter.DataViewHolder>(DataDiffCallback()) {
    class DataDiffCallback:DiffUtil.ItemCallback<ProductDataItem>() {
        override fun areItemsTheSame(oldItem: ProductDataItem, newItem: ProductDataItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductDataItem,
            newItem: ProductDataItem
        ): Boolean {
           return oldItem.product_name==newItem.product_name
        }

    }

    class DataViewHolder(private var binding:ViewHolderAllDataBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindData(productDataItem: ProductDataItem){
            Picasso.get().load(productDataItem.product_image).into(binding.allImage)
            binding.txtTitle.text=productDataItem.product_name
            binding.price.text=productDataItem.product_price.toString()

            binding.allImage.setOnClickListener{v->
                val context=v.context
                val intent=Intent(context,ProductDetailActivity::class.java).apply {
                    putExtra("product_image", productDataItem.product_image)
                    putExtra("product_name", productDataItem.product_name)
                    putExtra("product_price", productDataItem.product_price).toString()
                    putExtra("product_description", productDataItem.product_detail.product_description)
                }
                Log.d("product_price", productDataItem.product_price.toString())
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderAllDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindData(Data)
    }
}