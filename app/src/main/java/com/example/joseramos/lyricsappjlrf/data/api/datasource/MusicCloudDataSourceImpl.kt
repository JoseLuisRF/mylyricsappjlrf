package com.example.joseramos.lyricsappjlrf.data.api.datasource


import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.model.ApiResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class MusicCloudDataSourceImpl @Inject constructor(private val api: MusicMatchApi) : MusicCloudDataSource {

    override fun getTopSongs(country: String): LiveData<ApiResponse<BaseResponse<GetTopSongsResponse>>> {
        return api.getTopSongs(1, 5, "mx", 1)
    }

    override fun getSongLyrics(trackName: String, artistName: String): LiveData<ApiResponse<BaseResponse<LyricsWrapperResponse>>> {
        return api.getLyrics(trackName, artistName)
    }
}
