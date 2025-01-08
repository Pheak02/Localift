package com.local.lift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.api.AuthService
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch

class ResetPasswordViewModel : ViewModel() {

    private val authService = AuthService.create()

    private val _resetPasswordState = MutableLiveData<APIState<String>>()
    val resetPasswordState: LiveData<APIState<String>> get() = _resetPasswordState

    fun resetPassword(email: String, newHashedPassword: String) {
        viewModelScope.launch {
            _resetPasswordState.postValue(APIState.Loading)

            try {
                val usersResponse = authService.getUsers()
                if (usersResponse.isSuccessful) {
                    val users = usersResponse.body() ?: emptyMap()
                    Log.d("usersResponse", "Fetched users: $users")

                    val userId = users.entries.find { it.value.email == email }?.key
                    if (userId != null) {
                        Log.d("ResetPasswordViewModel", "User ID: $userId")

                        val updates = mapOf("hashedPassword" to newHashedPassword)
                        val updateResponse = authService.updatePassword(userId, updates)

                        if (updateResponse.isSuccessful) {
                            Log.d("ResetPasswordViewModel", "Password updated successfully")
                            _resetPasswordState.postValue(APIState.Success("Password reset successfully"))
                        } else {
                            val errorBody = updateResponse.errorBody()?.string()
                            Log.e("ResetPasswordViewModel", "Failed to update password: $errorBody")
                            _resetPasswordState.postValue(APIState.Error("Failed to reset password: $errorBody"))
                        }
                    } else {
                        Log.e("ResetPasswordViewModel", "User not found for email: $email")
                        _resetPasswordState.postValue(APIState.Error("User not found"))
                    }
                } else {
                    val errorBody = usersResponse.errorBody()?.string()
                    Log.e("ResetPasswordViewModel", "Failed to fetch users: $errorBody")
                    _resetPasswordState.postValue(APIState.Error("Failed to fetch users: $errorBody"))
                }
            } catch (e: Exception) {
                Log.e("ResetPasswordViewModel", "Error occurred: ${e.localizedMessage}")
                _resetPasswordState.postValue(APIState.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }
}
