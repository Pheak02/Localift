// AuthService.kt
package com.local.locallift.api

import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("login")
    suspend fun signIn(@Body credentials: SignInRequest): Response<SignInResponse>
}
