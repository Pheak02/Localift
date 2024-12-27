package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.local.locallift.R
import com.local.locallift.databinding.SuccessForgetPasswordBinding

class SuccessForgetPasswordFragment : Fragment() {

    private var _binding: SuccessForgetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SuccessForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToEmail.setOnClickListener {
            findNavController().navigate(R.id.action_successForgetPassword_to_newPwd)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
