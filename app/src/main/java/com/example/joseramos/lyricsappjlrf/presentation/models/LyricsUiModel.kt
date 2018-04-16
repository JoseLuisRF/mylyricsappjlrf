package com.example.joseramos.lyricsappjlrf.presentation.models

class LyricsUiModel: BaseModel() {

    var songLyrics: String = ""

    override fun toString(): String {
        return "LyricsUiModel(songLyrics='$songLyrics')"
    }

}
