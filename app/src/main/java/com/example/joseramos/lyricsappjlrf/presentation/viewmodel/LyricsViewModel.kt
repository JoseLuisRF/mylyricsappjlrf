package com.example.joseramos.lyricsappjlrf.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.models.LyricsUiModel
import javax.inject.Inject

class LyricsViewModel @Inject constructor(val musicRepository: MusicRepository, val dataMapper: MusicDataMapper) : ViewModel() {

    private var trackIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private var songLyrics: LiveData<LyricsUiModel>

    fun init() {

        musicRepository.fetchLyrics2(id, object : CallbackResponse<LiveData<LyricsModel>> {
            override fun onSuccess(response: LiveData<LyricsModel>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: LiveData<LyricsModel>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun getLyrics(): LiveData<LyricsUiModel> {
        return songLyrics
    }

    fun setTrackId(trackId: Int) {
        if (trackId != 0) {
            trackIdLiveData.value = trackId
        }
    }

}