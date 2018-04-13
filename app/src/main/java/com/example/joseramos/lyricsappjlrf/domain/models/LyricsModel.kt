package com.example.joseramos.lyricsappjlrf.domain.models

import com.example.joseramos.lyricsappjlrf.domain.models.base.BaseModel

data class LyricsModel constructor (
        val lyricsId: Int = 0,
        val trackId: Int = 0,
        val restricted: Int = 0,
        val instrumental: Int = 0,
        val lyricsBody: String = "",
        val lyricsLanguage: String = "",
        val scriptTrackingUrl: String = "",
        val pixelTrackingUrl: String = "",
        val htmlTrackingUrl: String = "",
        val lyricsCopyRight: String = "",
        val updatedTime: String = ""
) : BaseModel()
