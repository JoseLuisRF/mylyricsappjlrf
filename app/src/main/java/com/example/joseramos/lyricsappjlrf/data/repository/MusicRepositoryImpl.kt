package com.example.joseramos.lyricsappjlrf.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TopSongsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import javax.inject.Inject


class MusicRepositoryImpl @Inject constructor(
        private val deviceUtils: DeviceUtils,
        private val musicCloudDataSource: MusicCloudDataSource,
        private val musicDiskDataSource: MusicDiskDataSource,
        private val dataMapper: MusicDataMapper,
        private val appExecutors: AppExecutors) : MusicRepository {

    override fun fetchTopSongs(): LiveData<TopSongsModel> {
        val responseMediatorLiveData = MediatorLiveData<TopSongsModel>()

        if (deviceUtils.isNetworkAvailable) {
            responseMediatorLiveData.addSource(musicCloudDataSource.getTopSongs("mx"), { apiResponse ->
                if (apiResponse != null && apiResponse.isSuccessful && apiResponse.body != null) {
                    val models = apiResponse.body.message.body.trackList.map { trackWrapper -> dataMapper.convert(trackWrapper.track) }

                    appExecutors.diskIO.execute {
                        val entities = models.map { trackWrapper -> dataMapper.convert(trackWrapper) }
                        musicDiskDataSource.insertTopSong2(entities)
                    }

                    val topSongsModel = TopSongsModel()
                    topSongsModel.tracks.addAll(models)
                    responseMediatorLiveData.postValue(topSongsModel)

                } else {
                    val errorResponse = TopSongsModel()
                    errorResponse.setError("Tracks were not found")
                    responseMediatorLiveData.postValue(errorResponse)
                }
            })
        } else {
            val errorResponse = TopSongsModel()
            errorResponse.setError("No Internet Connection")
            responseMediatorLiveData.postValue(errorResponse)
        }

        return responseMediatorLiveData
    }

    override fun fetchLyrics(trackId: Int): LiveData<LyricsModel> {
        val mediatorResponse = MediatorLiveData<LyricsModel>()
        val entityLiveData = musicDiskDataSource.selectSong2(trackId)
        mediatorResponse.addSource(entityLiveData, { trackEntity ->
            if (trackEntity != null) {
                if (deviceUtils.isNetworkAvailable) {
                    val lyricsResponseLiveData = musicCloudDataSource.getSongLyrics(trackEntity.trackName, trackEntity.artistName)
                    mediatorResponse.addSource(lyricsResponseLiveData, { response ->
                        if (response != null && response.isSuccessful && response.body != null) {
                            val model = dataMapper.convert(trackId, response.body.message.body.lyrics)
                            if (!model.error) {
                                appExecutors.diskIO.execute {
                                    musicDiskDataSource.insertLyrics2(dataMapper.convert(model))
                                }
                            }
                            mediatorResponse.postValue(model)
                        } else {
                            val errorResponse = LyricsModel()
                            errorResponse.setError("Oops there was an error on our servers")
                            mediatorResponse.postValue(errorResponse)
                        }
                    })
                } else {
                    val errorResponse = LyricsModel()
                    errorResponse.setError("No Internet Connection")
                    mediatorResponse.postValue(errorResponse)
                }
            } else {
                val errorResponse = LyricsModel()
                errorResponse.setError("TrackId: $trackId was not found!")
                mediatorResponse.postValue(errorResponse)
            }

        })
        return mediatorResponse
    }
}