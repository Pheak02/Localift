package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.local.locallift.databinding.SignUpBinding
import com.local.lift.viewmodel.SignUpViewModel
import com.local.locallift.api.APIState
import com.local.locallift.R

class SignUpFragment : Fragment() {

    private var _binding: SignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the sign-up button
        binding.signUp.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val fullName = binding.fullnameInput.text.toString().trim()

            // Validate input fields
            if (email.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty()) {
                signUpViewModel.signUp(email, password, fullName)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate back to the sign-in screen
        binding.backToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        // Observe the sign-up state
        signUpViewModel.signUpState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is APIState.Loading -> {
                    // Show a simple toast for loading
                    Toast.makeText(requireContext(), "Registering user...", Toast.LENGTH_SHORT).show()
                }
                is APIState.Success -> {
                    // Show success message and navigate to the sign-in screen
                    Toast.makeText(requireContext(), state.data, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                }
                is APIState.Error -> {
                    // Show error message
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
