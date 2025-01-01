package com.local.lift.model

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T?
) {

    fun isSuccess(): Boolean {
        return status == "success"
    }
}
