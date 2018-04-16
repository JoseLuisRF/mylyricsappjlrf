package com.example.joseramos.lyricsappjlrf.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.models.LyricsUiModel
import javax.inject.Inject

class LyricsViewModel @Inject constructor(val musicRepository: MusicRepository, val dataMapper: MusicDataMapper) : ViewModel() {

    private var trackIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private var songLyrics: LiveData<LyricsUiModel>

    init {
        songLyrics = Transformations.switchMap(trackIdLiveData, { id ->
            Transformations.map(musicRepository.fetchLyrics(id), { domainModel ->
                dataMapper.convertTo(domainModel)
            })
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