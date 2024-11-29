package com.local.lift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.local.locallift.api.APIState

class SignUpViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    private val _signUpState = MutableLiveData<APIState<String>>()
    val signUpState: LiveData<APIState<String>> get() = _signUpState

    fun signUp(email: String, password: String, fullName: String) {
        // Notify UI about the loading state
        _signUpState.value = APIState.Loading

        // Create user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val userId = result.user?.uid ?: ""
                saveUserToDatabase(userId, email, fullName)
            }
            .addOnFailureListener { e ->
                _signUpState.value = APIState.Error("Sign up failed: ${e.message}")
            }
    }

    private fun saveUserToDatabase(userId: String, email: String, fullName: String) {
        val user = mapOf(
            "userId" to userId,
            "email" to email,
            "fullName" to fullName
        )

        // Save user data to Firebase Realtime Database
        database.getReference("users/$userId").setValue(user)
            .addOnSuccessListener {
                _signUpState.value = APIState.Success("User registered successfully.")
            }
            .addOnFailureListener { e ->
                _signUpState.value = APIState.Error("Failed to save user to database: ${e.message}")
            }
    }
}
