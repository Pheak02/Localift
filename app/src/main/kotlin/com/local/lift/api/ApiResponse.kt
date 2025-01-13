package com.local.lift.api

class ApiResponse<T> (
    val code: Int,
    val message: String,
    val data: T?
)