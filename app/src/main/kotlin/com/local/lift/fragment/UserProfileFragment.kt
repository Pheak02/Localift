package com.local.lift.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.local.lift.viewModel.SharedViewModel
import com.local.locallift.databinding.UserProfileBinding

class UserProfileFragment : Fragment() {

    // Using activityViewModels to share data across fragments
    private val sharedViewModel: SharedViewModel by activityViewModels()

    // View binding for the fragment
    private var _binding: UserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe sharedViewModel for user full name updates
        sharedViewModel.userFullName.observe(viewLifecycleOwner) { fullName ->
            binding.welcomeUser.text = "Welcome, $fullName"
            Log.d("UserProfileFragment", "User Full Name: $fullName")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
