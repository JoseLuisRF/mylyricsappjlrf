package com.example.joseramos.lyricsappjlrf.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.util.Log
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.data.repository.response.CallbackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class MusicRepositoryImpl @Inject constructor(
        private val musicCloudDataSource: MusicCloudDataSource,
        private val musicDiskDataSource: MusicDiskDataSource,
        private val dataMapper: MusicDataMapper,
        private val appExecutors: AppExecutors) : MusicRepository {

    override fun fetchTopSongs(callbackResponse: CallbackResponse<LiveData<List<TrackModel>>>) {
        val data = MutableLiveData<List<TrackModel>>()
        musicCloudDataSource
                .getTopSongs("mx")
                .enqueue(object : Callback<BaseResponse<GetTopSongsResponse>> {

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

                        //Save into the data base
                        appExecutors.diskIO.execute {
                            musicDiskDataSource.insertTopSong2(dataMapper.convert(models))
                            appExecutors.mainThread.execute {
                                callbackResponse.onSuccess(data)
                            }
                        }
                    }
                })
    }

    override fun saveLyrics(model: LyricsModel): Flowable<Int> {
        return musicDiskDataSource.insertLyrics(dataMapper.convert(model))
    }

    override fun getLyrics(trackId: Int): Flowable<LyricsModel> {
        return musicDiskDataSource.selectLyrics(trackId).map { entity -> dataMapper.convert(entity) }
    }


//    override fun getSong2(trackId: Int): LiveData<TrackModel> {
//        return Transformations.map(musicDiskDataSource.selectSong2(trackId), { entity ->
//            dataMapper.convert(entity)
//        })
//
//    }

    override fun fetchLyrics2(trackId: Int, callbackResponse: CallbackResponse<LiveData<LyricsModel>>) {


        appExecutors.diskIO.execute {
            val entity = musicDiskDataSource.selectSong2(trackId)
            Log.d("JLRF", "entity.toString():" + entity.toString())
            val response = musicCloudDataSource.getSongLyrics(
                    entity.trackName,
                    entity.artistName).execute()

            Log.d("JLRF", "response.toString():" + response.toString())

            if (response.isSuccessful) {

                val res = musicDiskDataSource.insertLyrics2(dataMapper.convert(dataMapper.convert(trackId, response.body().message.body.lyrics)))
                if (res > 0) {
                    val lyricsModel = musicDiskDataSource.selectLyrics2(res)

                    val lyricsSongLiveData = MutableLiveData<LyricsModel>()
                    lyricsSongLiveData.value = dataMapper.convert(lyricsModel)
                    callbackResponse.onSuccess(lyricsSongLiveData)
                }

            } else {
                val errorResponse = LyricsModel()
                errorResponse.hasError("Network Error")
                val lyricsSongLiveData = MutableLiveData<LyricsModel>()
                lyricsSongLiveData.value = errorResponse
                callbackResponse.onError(lyricsSongLiveData)
            }
        }


    }
}