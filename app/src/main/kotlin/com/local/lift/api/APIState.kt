// APIState.kt
package com.local.locallift.api

sealed class APIState<out T> {
    object Loading : APIState<Nothing>()
    data class Success<out T>(val data: T) : APIState<T>()
    data class Error(val message: String) : APIState<Nothing>()
}
