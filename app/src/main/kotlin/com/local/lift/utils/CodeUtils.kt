package com.local.lift.utils

object CodeUtils {
    fun generateVerificationCode(): String {
        return (1000..9999).random().toString()
    }
}
