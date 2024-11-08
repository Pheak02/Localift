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
        // Simulate a network delay
        delay(1000)

        // Load the user data from assets
        val signInResponse = AssetLoader.loadUsersFromAssets(context)

        // Check each user in the list to find a match
        val matchingUser = signInResponse.users.find {
            it.email == signInRequest.email && it.password == signInRequest.password
        }

        // Return success if a user is found, else return error
        return if (matchingUser != null) {
            Response.success(SignInResponse(listOf(matchingUser))) // Return the matched user info
        } else {
            Response.error(401, okhttp3.ResponseBody.create(null, "Invalid credentials"))
        }
    }
}

