package com.example.joseramos.lyricsappjlrf.presentation.models

data class TrackUIModel constructor(val trackId: Int,
                                    val trackName: String,
                                    val albumName: String,
                                    val albumId: Int,
                                    val artistName: String,
                                    val albumImageUrl: String,
                                    val favorite: Int)


class LyricsUIViewModel : BaseModel() {

    val topSongs = mutableListOf<TrackUIModel>()

}