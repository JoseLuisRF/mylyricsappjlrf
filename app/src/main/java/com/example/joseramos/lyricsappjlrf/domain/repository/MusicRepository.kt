package com.example.joseramos.lyricsappjlrf.domain.repository

import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import io.reactivex.Flowable


interface MusicRepository {

    fun getTopSongs() : Flowable<List<TrackModel>>
}