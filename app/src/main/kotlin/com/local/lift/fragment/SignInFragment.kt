package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.local.locallift.databinding.SignInBinding
import com.local.lift.viewmodel.SignInViewModel
import com.local.lift.viewmodel.SignInViewModelFactory
import com.local.locallift.api.APIState

class SignInFragment : Fragment() {

    private var _binding: SignInBinding? = null
    private val binding get() = _binding!!

    // Initialize ViewModel with SignInViewModelFactory that takes Context
    private val signInViewModel: SignInViewModel by viewModels {
        SignInViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the sign-in state to handle UI changes
        signInViewModel.signInState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is APIState.Loading -> {
                    // Optional: Show a loading indicator
                }
                is APIState.Success -> {
                    Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                }
                is APIState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set up button click listener for sign-in
        binding.signIn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInViewModel.signIn(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Forgot password listener
        binding.forgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
