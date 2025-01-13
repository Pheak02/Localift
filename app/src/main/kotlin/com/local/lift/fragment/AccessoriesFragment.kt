package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.local.lift.adapter.AccessoriesDataAdapter
import com.local.lift.api.State
import com.local.lift.viewModel.AccessoriesViewModel
import com.local.locallift.R
import com.local.locallift.databinding.FragmentAccessoriesBinding
import com.local.locallift.databinding.ViewHolderAccessoriesDataBinding


class AccessoriesFragment : Fragment() {
    private lateinit var binding: FragmentAccessoriesBinding
    private lateinit var accessoriesDataAdapter: AccessoriesDataAdapter
    private val viewModel by viewModels<AccessoriesViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAccessoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accessoriesDataAdapter= AccessoriesDataAdapter()
        binding.recycleData.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=accessoriesDataAdapter
        }
        viewModel.dataState.observe(viewLifecycleOwner){dataState->
            when(dataState.state){
                State.SUCCESS->accessoriesDataAdapter.submitList(dataState.data)
                State.ERROR-> Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadData()
    }

}