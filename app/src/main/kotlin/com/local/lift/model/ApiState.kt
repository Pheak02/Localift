package com.local.lift.model

data class ApiState<T>(
    val state: State,
    val data: T?
)

enum class State {
    loading, success, error
}
