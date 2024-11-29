package com.local.lift.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.local.locallift.databinding.SignInBinding
import com.local.lift.viewmodel.SignInViewModel
import com.local.lift.viewmodel.SignInViewModelFactory
import com.local.locallift.R
import com.local.locallift.api.APIState

class SignInFragment : Fragment() {

    private var _binding: SignInBinding? = null
    private val binding get() = _binding!!

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

        // Observe sign-in state
        signInViewModel.signInState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is APIState.Loading -> {
                    Log.d("SignInFragment", "Login in progress...")
                }
                is APIState.Success -> {
                    Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                    Log.d("SignInFragment", "Attempting to navigate...")

                    val navController = findNavController()
                    val currentDestination = navController.currentDestination
                    Log.d("SignInFragment", "Current destination: ${currentDestination?.label}")

                    // Check if the current destination is SignInFragment
                    if (currentDestination?.id == R.id.signInFragment) {
                        try {
                            navController.navigate(R.id.action_signInFragment_to_productFragment)
                            Log.d("SignInFragment", "Navigation to ProductFragment triggered successfully.")
                        } catch (e: Exception) {
                            Log.e("SignInFragment", "Navigation error: ${e.message}", e)
                        }
                    } else {
                        Log.e("SignInFragment", "Current destination is not SignInFragment. Navigation skipped.")
                    }
                }
                is APIState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    Log.e("SignInFragment", "Login error: ${state.message}")
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

        binding.backToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
