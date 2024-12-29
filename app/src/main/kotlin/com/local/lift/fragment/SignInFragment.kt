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
                    val user = state.data
                    if (user.status == "success") {
                        Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_signInFragment_to_productFragment)
                    } else {
                        Toast.makeText(requireContext(), "Login failed: ${user.status}", Toast.LENGTH_SHORT).show()
                        Log.e("SignInFragment", "Login failed with status: ${user.status}")
                    }
                }
                is APIState.Error -> {
                    Toast.makeText(requireContext(), "Your email or password is not correct.", Toast.LENGTH_SHORT).show()
                    Log.e("SignInFragment", "Login error: ${state.message}")
                }
            }
        }

        binding.signIn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInViewModel.signIn(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.forgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }

        binding.backToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgetPwdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
