package com.example.joseramos.lyricsappjlrf.data.repository.datasource


import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response

interface MusicCloudDataSource {

    fun getTopSongs(country: String): Call<BaseResponse<GetTopSongsResponse>>


    fun getSongLyrics(trackName: String, artistName: String): Flowable<Response<BaseResponse<LyricsWrapperResponse>>>
}
