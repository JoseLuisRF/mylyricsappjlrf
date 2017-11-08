package com.example.joseramos.lyricsappjlrf.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.joseramos.lyricsappjlrf.data.database.dao.base.BaseDao
import com.example.joseramos.lyricsappjlrf.data.database.entity.COLUMN_LYRICS_ID
import com.example.joseramos.lyricsappjlrf.data.database.entity.COLUMN_TRACK_ID
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TABLE_NAME_SONG_LYRICS

@Dao
interface LyricsDao : BaseDao<SongLyricsEntity> {

    @Query("select * from $TABLE_NAME_SONG_LYRICS where $COLUMN_TRACK_ID = :trackId")
    fun selectLyrics(trackId: Int): SongLyricsEntity

    @Query("select * from $TABLE_NAME_SONG_LYRICS")
    fun selectAll(): List<SongLyricsEntity>
}