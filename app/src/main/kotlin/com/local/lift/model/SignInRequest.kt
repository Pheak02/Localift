package com.local.lift.model

data class SignInRequest(
    val id: Int = 0,
    val email: String,
    val password: String,
    val rememberMe: Boolean = false
)
