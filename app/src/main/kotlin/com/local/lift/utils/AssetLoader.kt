package com.local.lift.util

import android.content.Context
import com.google.gson.Gson
import com.local.lift.model.SignInResponse
import java.io.InputStreamReader

object AssetLoader {
    fun loadUsersFromAssets(context: Context): SignInResponse {
        val inputStream = context.assets.open("users.json")
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, SignInResponse::class.java)
    }
}
