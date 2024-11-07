package com.local.lift.model

data class SignInResponse(
    val id: Int,
    val email: String,
    val rememberMe: Boolean
    // Add more fields here if needed, depending on your API response
)
