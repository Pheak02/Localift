package com.local.lift.model

data class ApiResponse<T>(
    val status: String? = null,
    val message: String?,
    val data: T?
) {

    fun isSuccess(): Boolean {
        return status == "success"
    }
}
