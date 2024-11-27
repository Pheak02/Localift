package com.local.lift.repository

import SignInRepository
import android.content.Context
import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import com.local.lift.util.AssetLoader
import kotlinx.coroutines.delay
import retrofit2.Response

class SignInRepositoryImpl(private val context: Context) : SignInRepository {

    override suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> {
        delay(1000)

        val signInResponse = AssetLoader.loadUsersFromAssets(context)
        val matchingUser = signInResponse.users.find {
            it.email == signInRequest.email && it.password == signInRequest.password
        }

        // Return success if a user is found, else return error
        return if (matchingUser != null) {
            Response.success(SignInResponse(listOf(matchingUser)))
        } else {
            Response.error(401, okhttp3.ResponseBody.create(null, "Invalid credentials"))
        }
    }
}

