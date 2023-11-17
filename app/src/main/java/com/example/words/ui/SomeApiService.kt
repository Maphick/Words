package com.example.words.ui

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException

interface RequestCallback {
    fun onSuccess(response: String)
    fun onFailure(error: String)
}

class SomeApiService () {

    private val client = OkHttpClient()
    val BASE_URL = "https://exercisedb.p.rapidapi.com/exercises/bodyPart/back?limit=100"

    fun makeRequest(callback: RequestCallback) {

        val jsonRequest = "your request body"
        // JSON
        //val body = jsonRequest.toRequestBody(JSON)
        //val request = Request.Builder().url(BASE_URL).post(body).build()

        //val body = jsonRequest.toRequestBody(JSON)
        val request = Request.Builder()
            .url(BASE_URL)
            .get()
            .addHeader("X-RapidAPI-Key", "6929bc99b9mshdb1a50796fb5502p111639jsn62b0b1df3969")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()

        //    post(body).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e.toString());
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw Exception("Запрос к серверу не был успешен:" +
                                " ${response.code} ${response.message}")
                    }
                    callback.onSuccess(response.body!!.string())
                }
            }


        })
    }
}

object SomeApi {
    val someService : SomeApiService by lazy {
        SomeApiService()
    }
}