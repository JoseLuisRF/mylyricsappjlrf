package com.example.joseramos.lyricsappjlrf.domain.interactor

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.NetworkBoundResource
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import javax.inject.Inject

class  UseCaseGetLyrics @Inject constructor(private val appExecutors: AppExecutors,
                                            private val musicRepository: MusicRepository) : NetworkBoundResource<LyricsModel, UseCaseGetLyrics.Params>(appExecutors) {

    override fun saveCallResult(item: LyricsModel) {
        musicRepository.insertLyrics(item)
    }

    override fun shouldFetch(data: LyricsModel?): Boolean {
        return data == null || data.error
    }

    override fun loadFromDb(params: Params): LiveData<LyricsModel> {
        return musicRepository.selectLyrics(params.songId)
    }

    override fun createCall(params: Params): LiveData<LyricsModel> {
        return musicRepository.fetchLyrics(params.songId)
    }

    data class Params constructor(val songId: Int)
}