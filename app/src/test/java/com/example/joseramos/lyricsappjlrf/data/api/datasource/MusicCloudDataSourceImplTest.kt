package com.example.joseramos.lyricsappjlrf.data.api.datasource

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.model.ApiResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.*

class MusicCloudDataSourceImplTest {

    private val api = Mockito.mock(MusicMatchApi::class.java)
    private lateinit var dataSource: MusicCloudDataSource

    @Before
    fun setup() {
        dataSource = MusicCloudDataSourceImpl(api)
    }

    @Test
    fun getTopSongs() {
        val response: LiveData<ApiResponse<BaseResponse<GetTopSongsResponse>>>
                = mock(LiveData::class.java) as LiveData<ApiResponse<BaseResponse<GetTopSongsResponse>>>

        given(api.getTopSongs(
                anyInt(),
                anyInt(),
                anyString(),
                anyInt()
        )).willReturn(response)

        dataSource.getTopSongs("test")

        verify(api, times(1)).getTopSongs(
                anyInt(),
                anyInt(),
                anyString(),
                anyInt())
    }

    @Test
    fun getSongLyrics() {

        val response: LiveData<ApiResponse<BaseResponse<LyricsWrapperResponse>>>
                = mock(LiveData::class.java) as LiveData<ApiResponse<BaseResponse<LyricsWrapperResponse>>>

        given(api.getLyrics(anyString(), anyString())).willReturn(response)


        dataSource.getSongLyrics("thisSong", "thisArtist")

        verify(api, times(1)).getLyrics(
                anyString(),
                anyString()
        )
    }
}