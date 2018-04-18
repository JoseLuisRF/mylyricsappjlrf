package com.example.joseramos.lyricsappjlrf.domain.interactor

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.NetworkBoundResource
import com.example.joseramos.lyricsappjlrf.domain.models.TopSongsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import javax.inject.Inject

class UseCaseGetTopSongs @Inject constructor(private val appExecutors: AppExecutors,
                                             private val musicRepository: MusicRepository) : NetworkBoundResource<TopSongsModel, TopSongsModel>(appExecutors) {

    init {
        //FIXME: Replace Result Object for BaseModel
        val loading = TopSongsModel()
        loading.status = "LOADING"
        init(loading)
    }

    override fun saveCallResult(item: TopSongsModel) {
        musicRepository.insertTopSongs(item)
    }

    override fun shouldFetch(data: TopSongsModel?): Boolean {
        return data == null || data.tracks.isEmpty()
    }

    override fun loadFromDb(): LiveData<TopSongsModel> {
        return musicRepository.selectTopSongs()
    }

    override fun createCall(): LiveData<TopSongsModel> {
        return musicRepository.fetchTopSongs()
    }
}