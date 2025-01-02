package com.local.lift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.model.User
import com.local.locallift.api.APIState
import com.local.lift.repository.SignInRepository
import kotlinx.coroutines.launch
import android.util.Log
import retrofit2.Response

class SignInViewModel(private val repository: SignInRepository) : ViewModel() {

    private val _signInState = MutableLiveData<APIState<User>>()
    val signInState: LiveData<APIState<User>> get() = _signInState

    fun signIn(email: String, password: String) {
        _signInState.value = APIState.Loading
        viewModelScope.launch {
            try {
                val userRequest = User(email = email, password = password)
                val response: Response<User> = repository.signIn(userRequest)

                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!
                    _signInState.value = APIState.Success(user)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("SignInViewModel", "Error body: $errorMessage")
                    _signInState.value = APIState.Error("Sign-in failed: ${response.message()} | Error: $errorMessage")
                }
            } catch (e: Exception) {
                _signInState.value = APIState.Error("An error occurred: ${e.localizedMessage}")
                Log.e("SignInViewModel", "Error: ${e.localizedMessage}", e)
            }
        }
    }
}
