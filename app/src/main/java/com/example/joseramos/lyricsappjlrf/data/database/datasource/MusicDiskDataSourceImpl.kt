package com.example.joseramos.lyricsappjlrf.data.database.datasource

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import javax.inject.Inject

class MusicDiskDataSourceImpl @Inject constructor(private val database: AppDataBase) : MusicDiskDataSource {

    override fun insertTopSong2(entity: List<TopSongsEntity>): List<Long> {
        return database.topSongsDao().insertAll2(entity)
    }

    override fun insertLyrics2(entity: SongLyricsEntity): Long {
        return database.lyricsDao().upsert(entity)
    }


    override fun selectLyrics2(trackId: Long):  LiveData<SongLyricsEntity> {
        return database.lyricsDao().selectLyrics(trackId)
    }

    override fun selectSong2(trackId: Int):
            LiveData<TopSongsEntity> = database.topSongsDao().selectSongById(trackId)

    override fun selectAllSongs(): LiveData<List<TopSongsEntity>> {
        return database.topSongsDao().selectAll()
    }
}