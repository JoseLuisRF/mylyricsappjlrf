package com.example.joseramos.lyricsappjlrf.domain.repository

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import io.reactivex.Flowable


interface MusicRepository {

    fun fetchTopSongs(callbackResponse: CallbackResponse<LiveData<List<TrackModel>>>)

    fun saveLyrics(model: LyricsModel): Flowable<Int>

    fun getLyrics(trackId: Int): Flowable<LyricsModel>

    fun fetchLyrics2(trackId: Int, callbackResponse: CallbackResponse<LiveData<LyricsModel>>)

//    fun getSong2(trackId: Int): LiveData<TrackModel>

}