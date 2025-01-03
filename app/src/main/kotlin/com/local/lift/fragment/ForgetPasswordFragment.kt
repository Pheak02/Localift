package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.local.lift.utils.CodeUtils
import com.local.lift.viewmodel.VerificationViewModel
import androidx.navigation.fragment.findNavController
import com.local.locallift.R
import com.local.locallift.databinding.ForgetPasswordBinding
import com.local.lift.utils.EmailUtils

class ForgetPasswordFragment : Fragment() {

    private var _binding: ForgetPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var verificationViewModel: VerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verificationViewModel = ViewModelProvider(requireActivity()).get(VerificationViewModel::class.java)

        binding.submit.setOnClickListener {
            val email = binding.emailInput.text.toString()

            if (email.isNotEmpty()) {
                val verificationCode = CodeUtils.generateVerificationCode()
                verificationViewModel.verificationCode = verificationCode
                verificationViewModel.email = email
                EmailUtils.sendVerificationEmail(email, verificationCode)
                findNavController().navigate(R.id.action_forgetPassword_to_forgetPwdCode)
            } else {
                binding.emailInput.error = "Email is required"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
