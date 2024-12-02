package com.local.lift.repository

import com.local.lift.model.User
import retrofit2.Response

interface SignInRepository {
    suspend fun signIn(user: User): Response<User>
}
