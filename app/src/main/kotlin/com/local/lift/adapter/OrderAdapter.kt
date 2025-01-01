package com.local.lift.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.local.lift.model.Order
import com.local.locallift.databinding.OrderHistoryItemBinding

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val orders = mutableListOf<Order>()

    fun submitList(orderList: List<Order>?) { //update new list of order - append data
        orders.clear()
        if (orderList != null) {
            orders.addAll(orderList)
        }
        notifyDataSetChanged() //refresh with new data change
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder { //create view for single list item
        val binding = OrderHistoryItemBinding.inflate(
           LayoutInflater.from(parent.context), parent, false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) { //bind
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    //represent each item
    class OrderViewHolder(private val binding: OrderHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.txtDate.text = order.date
            binding.txtBillingName.text = order.billingName
            binding.txtAmount.text = order.amount
            binding.txtStatus.text = order.status
            binding.txtOrderNumber.text = order.orderNumber
        }
    }
}