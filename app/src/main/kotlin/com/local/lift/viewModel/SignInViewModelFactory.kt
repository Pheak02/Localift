// SignInViewModelFactory.kt
package com.local.lift.viewmodel

import SignInRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.local.locallift.viewmodel.SignInViewModel

class SignInViewModelFactory(private val repository: SignInRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}