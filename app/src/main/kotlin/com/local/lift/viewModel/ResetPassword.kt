package com.local.lift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.model.User
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch
import com.local.lift.api.AuthService

class ResetPasswordViewModel : ViewModel() {

    private val authService = AuthService.create()

    private val _resetPasswordState = MutableLiveData<APIState<String>>()
    val resetPasswordState: LiveData<APIState<String>> get() = _resetPasswordState

    fun resetPassword(email: String, newPassword: String) {
        viewModelScope.launch {
            _resetPasswordState.postValue(APIState.Loading)

            try {
                val user = User(email = email, password = newPassword)
                val response = authService.updatePassword(email, user)

                if (response.isSuccessful) {
                    _resetPasswordState.postValue(APIState.Success("Password reset successfully"))
                } else {
                    _resetPasswordState.postValue(APIState.Error("Failed to reset password"))
                }
            } catch (e: Exception) {
                _resetPasswordState.postValue(APIState.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }
}
