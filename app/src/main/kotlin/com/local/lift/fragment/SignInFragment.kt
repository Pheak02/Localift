package com.local.lift.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.local.locallift.databinding.SignInBinding

class SignInFragment : Fragment() {
    private var binding: SignInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up button click listener
        binding?.signIn?.setOnClickListener {
            val email = binding?.emailInput?.text.toString()
            val password = binding?.passwordInput?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (isLoginValid(email, password)) {
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Invalid login credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.forgotPassword?.setOnClickListener {
            Toast.makeText(context, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLoginValid(email: String, password: String): Boolean {
        return email == "user@example.com" && password == "password" // Placeholder credentials
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
