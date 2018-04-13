package com.example.joseramos.lyricsappjlrf.data.repository.response

interface CallbackResponse<T> {

    fun onSuccess(response: T)

    fun onError(error: Throwable)

    fun onError(error: T)
}