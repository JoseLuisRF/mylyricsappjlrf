package com.example.joseramos.lyricsappjlrf.domain.models

data class LyricsModel constructor(
        val lyricsId: Int,
        val trackId: Int,
        val restricted: Int,
        val instrumental: Int,
        val lyricsBody: String,
        val lyricsLanguage: String,
        val scriptTrackingUrl: String,
        val pixelTrackingUrl: String,
        val htmlTrackingUrl: String,
        val lyricsCopyRight: String,
        val updatedTime: String
)
