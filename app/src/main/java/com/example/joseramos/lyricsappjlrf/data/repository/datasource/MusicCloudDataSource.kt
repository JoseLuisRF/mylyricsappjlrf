package com.example.joseramos.lyricsappjlrf.data.repository.datasource


import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.api.model.ApiResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import retrofit2.Call
import retrofit2.Response

interface MusicCloudDataSource {

    fun getTopSongs(country: String): LiveData<ApiResponse<BaseResponse<GetTopSongsResponse>>>


    fun getSongLyrics(trackName: String, artistName: String): LiveData<ApiResponse<BaseResponse<LyricsWrapperResponse>>>

}
