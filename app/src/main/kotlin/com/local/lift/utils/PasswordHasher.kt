package com.local.lift.utils

import java.security.MessageDigest

fun hashPassword(password: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val bytes = md.digest(password.toByteArray())
    val stringBuilder = StringBuilder()
    for (byte in bytes) {
        stringBuilder.append(String.format("%02x", byte))
    }
    return stringBuilder.toString()
}
