package com.example.joseramos.lyricsappjlrf.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import com.example.joseramos.lyricsappjlrf.data.database.dao.base.BaseDao
import com.example.joseramos.lyricsappjlrf.data.database.entity.COLUMN_TRACK_ID
import com.example.joseramos.lyricsappjlrf.data.database.entity.TABLE_NAME_TOP_SONGS
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity

@Dao
interface TopSongsDao : BaseDao<TopSongsEntity> {

    @Insert(onConflict = IGNORE)
    fun insertAll(entities: List<TopSongsEntity>): List<Long>

    @Query("select * from  $TABLE_NAME_TOP_SONGS ")
    fun selectAll(): List<TopSongsEntity>

    @Query("select * from  $TABLE_NAME_TOP_SONGS where $COLUMN_TRACK_ID = :trackId ")
    fun selectSongById(trackId: Int): TopSongsEntity
}