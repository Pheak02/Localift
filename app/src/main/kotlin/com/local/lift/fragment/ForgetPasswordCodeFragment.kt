package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.local.lift.viewmodel.VerificationViewModel
import com.local.locallift.R
import com.local.locallift.databinding.VerifiedForgetPasswordBinding

class ForgetPasswordCodeFragment : Fragment() {

    private var _binding: VerifiedForgetPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var verificationViewModel: VerificationViewModel
    private lateinit var verificationCode: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VerifiedForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel
        verificationViewModel = ViewModelProvider(requireActivity()).get(VerificationViewModel::class.java)

        // Retrieve the verification code from the ViewModel
        verificationCode = verificationViewModel.verificationCode ?: ""

        binding.submit.setOnClickListener {
            val enteredCode = getCodeFromInputs()

            if (enteredCode == verificationCode) {
                findNavController().navigate(R.id.action_forgetPasswordCode_to_successForgetPassword)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Invalid code. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getCodeFromInputs(): String {
        val digit1 = binding.digit1.text.toString().trim()
        val digit2 = binding.digit2.text.toString().trim()
        val digit3 = binding.digit3.text.toString().trim()
        val digit4 = binding.digit4.text.toString().trim()
        return digit1 + digit2 + digit3 + digit4
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
