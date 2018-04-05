package com.example.joseramos.lyricsappjlrf.data.api.datasource

import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MusicCloudDataSourceImplTest {



    @Test
    fun getTopSongs() {
        val data  = mock(MusicCloudDataSource::class.java)
        val result  = data.getTopSongs("test")
        //verify(data).getTopSongs("test").isEmpty
    }

    @Test
    fun getSongLyrics() {
    }
}