package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.local.lift.adapter.OrderAdapter
import com.local.lift.model.State
import com.local.lift.viewModel.OrderViewModel
import com.local.locallift.R
import com.local.locallift.databinding.OrderHistoryBinding


class OrderHistoryFragment : Fragment() {

    private val viewModel by viewModels<OrderViewModel>()
    private lateinit var binding: OrderHistoryBinding
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = OrderHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderAdapter = OrderAdapter()
        binding.recyclerViewOrders.adapter = orderAdapter

        setupSpinner()
        observeViewModel()

        viewModel.loadOrders()
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFilter.adapter = adapter
        }

        binding.spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Filter logic can be implemented here
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun observeViewModel() {
        viewModel.orderState.observe(viewLifecycleOwner) { state ->
            when (state.state) {
                State.loading -> binding.progressBar.visibility = View.VISIBLE
                State.success -> {
                    binding.progressBar.visibility = View.GONE
                    orderAdapter.submitList(state.data)
                }
                State.error -> binding.progressBar.visibility = View.GONE
            }
        }
    }

}