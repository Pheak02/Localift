package com.local.lift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.model.User
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch
import com.local.lift.api.AuthService

class SignUpViewModel : ViewModel() {
    private val authService = AuthService.create()

    private val _signUpState = MutableLiveData<APIState<String>>()
    val signUpState: LiveData<APIState<String>> get() = _signUpState

    fun signUp(user: User) {
        viewModelScope.launch {
            _signUpState.postValue(APIState.Loading)

            try {
                val response = authService.signUp(user)
                if (response.isSuccessful) {
                    _signUpState.postValue(APIState.Success("User registered successfully"))
                } else {
                    _signUpState.postValue(APIState.Error("Sign Up failed"))
                }
            } catch (e: Exception) {
                _signUpState.postValue(APIState.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }
}
