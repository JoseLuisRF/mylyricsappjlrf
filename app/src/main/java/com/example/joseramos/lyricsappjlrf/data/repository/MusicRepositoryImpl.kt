package com.example.joseramos.lyricsappjlrf.data.repository

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse


class MusicRepositoryImpl @Inject constructor(
        private val musicCloudDataSource: MusicCloudDataSource,
        private val musicDiskDataSource: MusicDiskDataSource,
        private val dataMapper: MusicDataMapper,
        private val appExecutors: AppExecutors) : MusicRepository {

    override fun fetchTopSongs(callbackResponse: CallbackResponse<LiveData<List<TrackModel>>>) {
        val data = MutableLiveData<List<TrackModel>>()

        musicCloudDataSource.getTopSongs("mx").enqueue(object : Callback<BaseResponse<GetTopSongsResponse>> {

            override fun onFailure(response: Call<BaseResponse<GetTopSongsResponse>>?, t: Throwable) {
                callbackResponse.onError(t)
            }

            override fun onResponse(call: Call<BaseResponse<GetTopSongsResponse>>?, response: Response<BaseResponse<GetTopSongsResponse>>?) {
                val models = mutableListOf<TrackModel>()
                if (response != null && response.isSuccessful) {
                    response.body().message.body.trackList.forEach { wrapper -> models.add(dataMapper.convert(wrapper.track)) }
//                    val models = response.body().message.body.trackList.map { trackWrapper -> dataMapper.convert(trackWrapper.track)}
                    data.value = models
                }
                data.value = models
                appExecutors.diskIO.execute {
                    musicDiskDataSource.insertTopSong2(dataMapper.convert(models))
                    appExecutors.mainThread.execute {
                        callbackResponse.onSuccess(data)
                    }
                }
            }
        })
    }

    override fun saveTopSong(models: List<TrackModel>): Flowable<Void> {
        return musicDiskDataSource.insertTopSong(dataMapper.convert(models)).map { null }
    }

    override fun getTopSongs(): Flowable<List<TrackModel>> {
        return musicDiskDataSource.selectTopSongs().map { entities -> dataMapper.convertToModels(entities) }
    }

    override fun fetchLyrics(trackName: String, artistName: String, trackId: Int): Flowable<LyricsModel> {
        return musicCloudDataSource.getSongLyrics(trackName, artistName).map { response ->
            if (!response.isSuccessful) {
                throw Exception("Something went wrong!")
            }

            if (!response.body().message.header.isSuccessful()) {
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
        return musicDiskDataSource.selectSong(trackId).map { entity -> dataMapper.convert(entity) }
    }
}