package com.example.joseramos.lyricsappjlrf.domain.models

import com.example.joseramos.lyricsappjlrf.domain.models.base.BaseModel


data class TrackModel constructor(val trackId: Int = 0,
                                  val trackName: String = "",
                                  val albumName: String = "",
                                  val albumId: Int = 0,
                                  val artistName: String = "",
                                  val albumImageUrl: String = "",
                                  val favorite: Int = 0) : BaseModel()

class TopSongsModel : BaseModel() {
    val tracks = mutableListOf<TrackModel>()
}


