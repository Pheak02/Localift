package com.local.locallift.api

sealed class APIState<out T> {
    data class Success<out T>(val data: T) : APIState<T>()
    data class Error(val message: String) : APIState<Nothing>()
    object Loading : APIState<Nothing>()
}
