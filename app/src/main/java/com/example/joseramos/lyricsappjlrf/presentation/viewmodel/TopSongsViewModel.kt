package com.example.joseramos.lyricsappjlrf.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.models.LyricsUIViewModel
import javax.inject.Inject

class TopSongsViewModel @Inject constructor(
        private val musicRepository: MusicRepository,
        private val dataMapper: MusicDataMapper) : ViewModel() {

    private var topSongs: LiveData<LyricsUIViewModel>

    init {
        topSongs = Transformations.map(musicRepository.fetchTopSongs(), { response ->
            dataMapper.convertTo(response)
        })
    }

    fun loadTopSongs(): LiveData<LyricsUIViewModel> {
        return topSongs
    }
}