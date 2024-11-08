package com.local.lift.model


data class ApiResponse<T>(
    val status: String,
    val data: T? = null,
    val message: String? = null
){

    fun isSuccess(): Boolean {
        return status == "success"
    }

}