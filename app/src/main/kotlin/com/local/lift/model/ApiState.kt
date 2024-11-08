package com.local.lift.model

data class ApiState<T>(
    val state: State,
    val data: T? = null,
)
enum class State {
    loading, success, error
}

