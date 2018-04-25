package com.example.joseramos.lyricsappjlrf.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetLyrics
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.presentation.models.LyricsUiModel
import javax.inject.Inject

class LyricsViewModel @Inject constructor(private val useCaseGetLyrics: UseCaseGetLyrics,
                                          private val dataMapper: MusicDataMapper) : ViewModel() {

    private var trackIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private var songLyrics: LiveData<LyricsUiModel>

    init {
        songLyrics = Transformations.switchMap(trackIdLiveData, { id ->
            if (id != 0) {
                Transformations.map(
                        //FIXME: Create a Factory to supply a Loading BaseModel
                        useCaseGetLyrics.execute(LyricsModel(), UseCaseGetLyrics.Params(id)),
                        { domainModel ->
                            dataMapper.convertTo(domainModel)
                        })
            } else {
                //FIXME: Create an errorFactory for BaseModel Errors
                val errorLiveData = MutableLiveData<LyricsUiModel>()
                val model = LyricsUiModel()
                model.setError("ID not found")
                errorLiveData.postValue(model)
                errorLiveData
            }

        })
    }

    fun getLyrics(): LiveData<LyricsUiModel> {
        return songLyrics
    }

    fun setTrackId(trackId: Int) {
        trackIdLiveData.value = trackId

    }
}