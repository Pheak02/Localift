package com.local.lift.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.local.lift.viewmodel.ResetPasswordViewModel
import com.local.lift.viewmodel.VerificationViewModel
import com.local.locallift.R
import com.local.locallift.api.APIState
import com.local.locallift.databinding.NewPasswordBinding
import com.local.lift.utils.hashPassword

class NewPasswordFragment : Fragment() {

    private var _binding: NewPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel
    private lateinit var verificationViewModel: VerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verificationViewModel = ViewModelProvider(requireActivity()).get(VerificationViewModel::class.java)
        val email = verificationViewModel.email

        if (email.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Email is missing", Toast.LENGTH_SHORT).show()
            return
        }

        resetPasswordViewModel = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)

        resetPasswordViewModel.resetPasswordState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is APIState.Loading -> Log.d("NewPasswordFragment", "Reset in progress...")
                is APIState.Success -> {
                    Toast.makeText(requireContext(), "Password reset successful!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_newPassword_to_signIn)
                }
                is APIState.Error -> {
                    Log.e("NewPasswordFragment", "Reset error: ${state.message}")
                }
            }
        }

        binding.resetPassword.setOnClickListener {
            val newPassword = binding.newPassword.text.toString().trim()
            val confirmPassword = binding.confirmPassword.text.toString().trim()

            if (validateInput(newPassword, confirmPassword)) {
                val hashedPassword = hashPassword(newPassword)

                resetPasswordViewModel.resetPassword(email, hashedPassword)
                Log.e("Email", email)
                Log.e("Hashed Password", hashedPassword)
            } else {
                Toast.makeText(requireContext(), "Passwords do not match or are empty.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateInput(newPassword: String, confirmPassword: String): Boolean {
        if (newPassword.isEmpty()) {
            binding.newPassword.error = "Password cannot be empty"
            return false
        }

        if (confirmPassword.isEmpty()) {
            binding.confirmPassword.error = "Confirm password cannot be empty"
            return false
        }

        if (newPassword != confirmPassword) {
            binding.confirmPassword.error = "Passwords do not match"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
