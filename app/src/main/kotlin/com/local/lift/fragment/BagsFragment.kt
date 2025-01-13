package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.local.lift.adapter.BagDataAdapter
import com.local.lift.api.State
import com.local.lift.viewModel.BagDataViewModel
import com.local.locallift.R
import com.local.locallift.databinding.FragmentBagsBinding

class BagsFragment : Fragment() {
    private lateinit var binding: FragmentBagsBinding
    private lateinit var bagDataAdapter: BagDataAdapter
    private val viewModel by viewModels<BagDataViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentBagsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bagDataAdapter= BagDataAdapter()
        binding.recycleData.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=bagDataAdapter
        }
        viewModel.dataState.observe(viewLifecycleOwner){dataState->
            when(dataState.state){
                State.SUCCESS->bagDataAdapter.submitList(dataState.data)
                State.ERROR-> Toast.makeText(context,"error", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadData()
    }
}