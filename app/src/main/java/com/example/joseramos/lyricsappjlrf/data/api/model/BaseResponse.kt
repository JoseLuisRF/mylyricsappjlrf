package com.example.joseramos.lyricsappjlrf.data.api.model

import com.google.gson.annotations.SerializedName


data class BaseResponse<T> constructor(
        @SerializedName("message")
        val message: Message<T>
)

data class Message<T> constructor(
        @SerializedName("header")
        val header: Header,
        @SerializedName("body")
        val body: T
)

data class Header(
        @SerializedName("status_code")
        val statusCode: Int,
        @SerializedName("execute_time")
        val executionTime: Float
) {
    fun isSuccessful(): Boolean {
        return statusCode == 200
    }

}
