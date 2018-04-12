package com.example.joseramos.lyricsappjlrf.data.api.datasource

import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.*
import retrofit2.Call

class MusicCloudDataSourceImplTest {

    private val api = Mockito.mock(MusicMatchApi::class.java);
    private lateinit var dataSource: MusicCloudDataSource

    @Before
    fun setup() {
        dataSource = MusicCloudDataSourceImpl(api)
    }

    @Test
    fun getTopSongs() {
        val response = mock(Call::class.java) as Call<BaseResponse<GetTopSongsResponse>>

        given(api.getTopSongs(ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt())).willReturn(response)

        dataSource.getTopSongs("test")

        verify(api, times(1)).getTopSongs(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt())
    }

    @Test
    fun getSongLyrics() {
    }
}