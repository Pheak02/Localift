package com.local.lift.api

import com.local.lift.model.ApiResponse
import com.local.lift.model.Order
import retrofit2.http.GET

interface ApiService {
    @GET("order_history.json")
    suspend fun loadOrders(): ApiResponse<List<Order>>
}