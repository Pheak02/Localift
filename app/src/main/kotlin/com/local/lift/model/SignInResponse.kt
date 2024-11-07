package com.local.lift.model

// SignInResponse.kt
data class SignInResponse(
    val token: String,
    val userId: String
    // Add any additional fields you receive from the API
)