package com.example.words

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.words.ui.WorcoutListEntyty
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


// https://www.youtube.com/watch?v=8zPkbV4INGA
// Gson Android Kotlin Tutorial - Parse Generic Lists from JSON & Introduction

class Repository {
    val BASE_URL = "https://exercisedb.p.rapidapi.com/exercises/bodyPart/back?limit=10"

    public fun makeRequest() {
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val okHttpClient = OkHttpClient()
        val parsedResponse = parseResponse(okHttpClient.newCall(
            createRequest())
            .execute()
        )
        println(parsedResponse)
    }

    fun createRequest():Request {
        return Request.Builder()
            .url(BASE_URL)
            .get()
            .addHeader("X-RapidAPI-Key", "6929bc99b9mshdb1a50796fb5502p111639jsn62b0b1df3969")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()
    }

    fun parseResponse(response: Response): String {
        val body = response.body?.string() ?: ""
        var gson = Gson()

        var mMineUserEntity = gson.fromJson(body, mutableListOf<WorcoutListEntyty.OneExerciseEntity>().javaClass)
        println(mMineUserEntity)
        return body
    }

}



