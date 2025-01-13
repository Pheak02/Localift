package com.local.lift.api

<<<<<<< Updated upstream
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager { //api client

    private var apiService: ApiService? =null

    fun getApiService(): ApiService {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://locallift-aeb0d-default-rtdb.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }

=======
import com.local.lift.service.DataService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager private constructor(){
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)  // Increased connection timeout
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://locallift-aeb0d-default-rtdb.firebaseio.com")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val dataService: DataService = retrofit.create(DataService::class.java)
    companion object {
        private var instance: ApiManager? = null

        fun getDataService(): DataService {
            if (instance == null) {
                instance = ApiManager()
            }
            return instance!!.dataService
        }
    }
>>>>>>> Stashed changes
}