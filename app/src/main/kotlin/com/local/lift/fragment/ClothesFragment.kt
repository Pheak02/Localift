package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.local.lift.adapter.ClothesDataAdapter
import com.local.lift.api.State
import com.local.lift.viewModel.ClothesDataViewModel
import com.local.locallift.R
import com.local.locallift.databinding.FragmentClothesBinding


class ClothesFragment : Fragment() {
    private lateinit var binding: FragmentClothesBinding
    private lateinit var clothesDataAdapter: ClothesDataAdapter
    private val viewModel by viewModels<ClothesDataViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClothesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clothesDataAdapter= ClothesDataAdapter()

        binding.recycleData.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=clothesDataAdapter

        }
        viewModel.dataState.observe(viewLifecycleOwner){dataState->
            when(dataState.state){
                State.SUCCESS->clothesDataAdapter.submitList(dataState.data)
                State.ERROR->Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadData()
    }

}