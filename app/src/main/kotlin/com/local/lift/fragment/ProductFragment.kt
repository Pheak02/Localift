package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.local.locallift.R
import com.local.locallift.databinding.ProductBinding

class ProductFragment : Fragment() {

    private var _binding: ProductBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productToCat.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_category1Fragment)
        }
        binding.productToUserPf.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_userProfileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
