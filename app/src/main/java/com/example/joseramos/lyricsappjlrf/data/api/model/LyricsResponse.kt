package com.example.joseramos.lyricsappjlrf.data.api.model

import com.google.gson.annotations.SerializedName

data class LyricsWrapperResponse constructor(
        @SerializedName("lyrics")
        val lyrics: LyricsResponse)

data class LyricsResponse constructor(
        @SerializedName("lyrics_id")
        val lyricsId: Int,

        @SerializedName("restricted")
        val restricted: Int,

        @SerializedName("instrumental")
        val instrumental: Int,

        @SerializedName("lyrics_body")
        val lyricsBody: String,

        @SerializedName("lyrics_language")
        val lyricsLanguage: String,

        @SerializedName("script_tracking_url")
        val scriptTrackingUrl: String,

        @SerializedName("pixel_tracking_url")
        val pixelTrackingUrl: String,

        @SerializedName("html_tracking_url")
        val htmlTrackingUrl: String,
        @SerializedName("lyrics_copyright")
        val lyricsCopyRight: String,

        @SerializedName("updated_time")
        val updatedTime: String
)