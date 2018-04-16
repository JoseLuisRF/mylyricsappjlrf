package com.example.joseramos.lyricsappjlrf.presentation.models

open class BaseModel {

    var error: Boolean = false
    var message: String = ""
    var status: String = "SUCCESS"

    fun setError(message: String) {
        this.message = message
        this.error = true
        this.status = "ERROR"
    }
}
