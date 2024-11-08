// SignInViewModel.kt
package com.local.lift.viewmodel

import SignInRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel(private val repository: SignInRepository) : ViewModel() {

    private val _signInState = MutableLiveData<APIState<SignInResponse>>()
    val signInState: LiveData<APIState<SignInResponse>> get() = _signInState

    fun signIn(email: String, password: String) {
        _signInState.value = APIState.Loading
        viewModelScope.launch {
            try {
                val response: Response<SignInResponse> = repository.signIn(
                    SignInRequest(id = 0, email = email, password = password, rememberMe = false)
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _signInState.value = APIState.Success(it)
                    } ?: run {
                        _signInState.value = APIState.Error("Unknown error occurred")
                    }
                } else {
                    _signInState.value = APIState.Error("Sign-in failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _signInState.value = APIState.Error("An error occurred: ${e.localizedMessage}")
            }
        }
    }
}
