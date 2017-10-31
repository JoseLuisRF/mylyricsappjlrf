package com.example.joseramos.lyricsappjlrf.data.repository

import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import java.lang.Exception
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(val musicCloudDataSource: MusicCloudDataSource,
                                              val dataMapper: MusicDataMapper) : MusicRepository {

    override fun getTopSongs(): Flowable<List<TrackModel>> {
        return musicCloudDataSource.getTopSongs("mx")
                .map { response ->
                    if (!response.isSuccessful) {
                        throw  Exception("Error while calling the API ")
                    }
                    val models: MutableList<TrackModel> = mutableListOf()

                    val tracksResponse = response.body().copy()
                    for (track in tracksResponse.message.body.trackList){
                        models.add(dataMapper.convert(track))
                    }
                    models
                }
    }

}