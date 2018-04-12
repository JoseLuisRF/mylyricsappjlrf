package com.example.joseramos.lyricsappjlrf.domain.repository

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import io.reactivex.Flowable


interface MusicRepository {

    fun fetchTopSongs(callbackResponse: CallbackResponse<LiveData<List<TrackModel>>>)

    fun getTopSongs() : Flowable<List<TrackModel>>

    fun saveTopSong(models: List<TrackModel>) : Flowable<Void>

    fun fetchLyrics(trackName: String, artistName: String, trackId: Int): Flowable<LyricsModel>

    fun saveLyrics(model: LyricsModel): Flowable<Int>

    fun getLyrics(trackId: Int): Flowable<LyricsModel>

    fun getSong(trackId: Int): Flowable<TrackModel>

}