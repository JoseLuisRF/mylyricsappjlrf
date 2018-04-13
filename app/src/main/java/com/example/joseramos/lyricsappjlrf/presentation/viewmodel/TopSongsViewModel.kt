package com.example.joseramos.lyricsappjlrf.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import android.arch.lifecycle.ViewModel
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper

import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.models.TrackUIModel
import javax.inject.Inject

class TopSongsViewModel @Inject constructor(
        private val musicRepository: MusicRepository,
        private val dataMapper: MusicDataMapper) : ViewModel() {

    private var topSongs: MutableLiveData<List<TrackUIModel>> = MutableLiveData()

    fun init() {
        musicRepository.fetchTopSongs(object : CallbackResponse<LiveData<List<com.example.joseramos.lyricsappjlrf.domain.models.TrackModel>>> {
            override fun onSuccess(response: LiveData<List<com.example.joseramos.lyricsappjlrf.domain.models.TrackModel>>) {
                topSongs.postValue(response.value?.map { input -> dataMapper.convertToUIModel(input) })
            }

            override fun onError(error: Throwable) {
                error.printStackTrace()
                topSongs = MutableLiveData()

            }

            override fun onError(error: LiveData<List<TrackModel>>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun loadTopSongs(): LiveData<List<TrackUIModel>> {
        return topSongs
    }
}