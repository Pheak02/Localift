package com.local.lift.api

import com.local.lift.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {


    @POST("users.json")
    suspend fun signUp(@Body user: User): Response<Void>

    @GET("users.json")
    suspend fun signIn(@Query("email") email: String): Response<Map<String, User>>

    // Companion object to create Retrofit instance
    companion object {
        private const val BASE_URL = "https://locallift-aeb0d-default-rtdb.firebaseio.com/"

        fun create(): AuthService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthService::class.java)
        }
    }
}
