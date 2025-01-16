package com.local.lift.model

data class ApiState<T>(
    val state: State,
    val data: T?,
    val status: String? = null // Add this field for status
)

enum class State {
    loading, success, error
}
