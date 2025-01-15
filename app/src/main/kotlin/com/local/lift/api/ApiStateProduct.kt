package com.local.lift.api

data class ApiStateProduct<T> (
    val state: State,
    val data: T? = null,
    val message: String? = null
)
enum class State {
    SUCCESS, ERROR
}