package com.local.lift.repository

import com.local.lift.api.AuthService
import com.local.lift.model.User
import com.local.lift.utils.hashPassword
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class SignInRepositoryImpl(private val authService: AuthService) : SignInRepository {

    override suspend fun signIn(user: User): Response<User> {

        val response = authService.signIn(user.email)

        if (response.isSuccessful) {
            val users = response.body()
            users?.forEach { (_, firebaseUser) ->
                if (firebaseUser.email == user.email && firebaseUser.hashedPassword == hashPassword(user.password!!)) {
                    return Response.success(firebaseUser)
                }
            }
            return Response.error(404, "Invalid email or password".toResponseBody())
        } else {
            return Response.error(response.code(), response.errorBody() ?: "Error fetching data".toResponseBody())
        }
    }
}
