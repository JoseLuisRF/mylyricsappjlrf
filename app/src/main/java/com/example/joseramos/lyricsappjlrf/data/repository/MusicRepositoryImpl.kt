package com.example.joseramos.lyricsappjlrf.data.repository

import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import java.lang.Exception
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val musicCloudDataSource: MusicCloudDataSource,
                                              private val musicDiskDataSource: MusicDiskDataSource,
                                              private val dataMapper: MusicDataMapper) : MusicRepository {

    override fun fetchTopSongs(): Flowable<List<TrackModel>> {
        return musicCloudDataSource
                .getTopSongs(1,5,"mx",1)
                .map { response ->
                    if (!response.isSuccessful) {
                        throw  Exception("Error while calling the API ")
                    }
                    val models: MutableList<TrackModel> = mutableListOf()
                    val trackList = response.body().message.body.trackList

                    for (trackWrapper in trackList) {
                        models.add(dataMapper.convert(trackWrapper.track))
                    }
                    models
                }
    }

    override fun saveTopSong(models: List<TrackModel>): Flowable<Void> {
        return musicDiskDataSource.insertTopSong( dataMapper.convert(models)).map { null }
    }

    override fun getTopSongs(): Flowable<List<TrackModel>> {
        return musicDiskDataSource.selectTopSongs().map { entities -> dataMapper.convertToModels(entities) }
    }

    override fun fetchLyrics(trackName: String, artistName: String, trackId: Int): Flowable<LyricsModel> {
        return musicCloudDataSource.getSongLyrics(trackName, artistName).map { response ->
            if(!response.isSuccessful){
                throw Exception("Something went wrong!")
            }

            if( !response.body().message.header.isSuccessful()){
                throw Exception("there was an error in the API")
            }

            dataMapper.convert(trackId, response.body().message.body.lyrics)
        }
    }

    override fun saveLyrics(model: LyricsModel): Flowable<Int> {
        return musicDiskDataSource.insertLyrics(dataMapper.convert(model))
    }

    override fun getLyrics(trackId: Int): Flowable<LyricsModel> {
        return musicDiskDataSource.selectLyrics(trackId).map { entity -> dataMapper.convert(entity) }
    }

    override fun getSong(trackId: Int): Flowable<TrackModel> {
        return musicDiskDataSource.selectSong(trackId).map { entity -> dataMapper.convert(entity)}
    }
}