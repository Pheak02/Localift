package com.local.lift.service

import com.local.lift.api.ApiResponse
import com.local.lift.model.Order
import com.local.lift.model.ProductDataItem
import retrofit2.http.GET

interface DataService {
    @GET("/product.json")
    suspend fun loadDataDisplay(): ApiResponse<List<ProductDataItem>>

    @GET("order_history.json")
    suspend fun loadOrders(): com.local.lift.model.ApiResponse<List<Order>>
}