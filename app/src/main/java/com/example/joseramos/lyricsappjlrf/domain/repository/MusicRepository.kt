package com.example.joseramos.lyricsappjlrf.domain.repository

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TopSongsModel


interface MusicRepository {

    fun fetchTopSongs(): LiveData<TopSongsModel>

    fun selectTopSongs(): LiveData<TopSongsModel>

    fun fetchLyrics(trackId: Int): LiveData<LyricsModel>

    fun insertTopSongs(topSongsModel: TopSongsModel): List<Long>

}