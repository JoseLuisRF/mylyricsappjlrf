package com.example.joseramos.lyricsappjlrf.domain.models


data class TrackModel constructor(val trackId: Int,
                                  val trackName: String,
                                  val albumName: String,
                                  val albumId: Int,
                                  val artistName: String,
                                  val albumImageUrl: String,
                                  val favorite: Int)


