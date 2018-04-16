package com.example.joseramos.lyricsappjlrf.domain.repository

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TopSongsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.presentation.models.BaseModel
import io.reactivex.Flowable


interface MusicRepository {

    fun fetchTopSongs(): LiveData<TopSongsModel>

    fun fetchLyrics(trackId: Int): LiveData<LyricsModel>

}