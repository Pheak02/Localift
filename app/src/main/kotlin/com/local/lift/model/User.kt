package com.local.lift.model

data class User(
    val userId: String? = null,
    val fullName: String? = null,
    val email: String,
    val password: String? = null,
    val hashedPassword: String? = null,
    val role: String = "user"
)
