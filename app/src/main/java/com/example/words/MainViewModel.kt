package com.example.words

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.ui.RequestCallback
import com.example.words.ui.SomeApi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient


// 1
class MainViewModel() : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val client = OkHttpClient()

    fun getResponseFromApi() {
        viewModelScope.launch {
            SomeApi.someService.makeRequest(object : RequestCallback {
                override fun onSuccess(response: String) {
                    _response.postValue(response)
                }

                override fun onFailure(error: String) {
                    Log.d(TAG, "Ошибка подключения: $error")
                }
            })
        }
    }
}