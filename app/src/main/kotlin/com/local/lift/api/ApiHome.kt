package com.local.lift.api

import com.local.lift.model.ApiResponse
import com.local.lift.model.HomepageData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiHome {
    @GET("category.json")
    suspend fun homePage(): ApiResponse<List<HomepageData>>

    companion object {
        private const val BASE_URL = "https://locallift-aeb0d-default-rtdb.firebaseio.com/"

        fun create(): ApiHome {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiHome::class.java)
        }
    }
}