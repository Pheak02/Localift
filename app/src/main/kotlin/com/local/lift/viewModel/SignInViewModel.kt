// SignInViewModel.kt
package com.local.locallift.viewmodel

import SignInRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel(private val repository: SignInRepository) : ViewModel() {

    val signInState = MutableLiveData<APIState<SignInResponse>>()

    fun signIn(email: String, password: String) {
        signInState.value = APIState.Loading
        viewModelScope.launch {
            val response: Response<SignInResponse> = repository.signIn((SignInRequest(email, password)))
            if (response.isSuccessful) {
                response.body()?.let {
                    signInState.value = APIState.Success(it)
                } ?: run {
                    signInState.value = APIState.Error("Unknown error occurred")
                }
            } else {
                signInState.value = APIState.Error("Sign-in failed: ${response.message()}")
            }
        }
    }
}
