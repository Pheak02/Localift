package com.local.lift.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.local.locallift.databinding.FragmentLandingBinding
import com.local.locallift.R

class LandingFragment : Fragment() {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextPage.setOnClickListener {
            Log.d("LandingFragment", "Next button clicked")
            try {
                findNavController().navigate(R.id.action_landingFragment_to_homePageFragment)
            } catch (e: Exception) {
                Log.e("LandingFragment", "Navigation error", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
