package com.example.joseramos.lyricsappjlrf.data.database.dao.base

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.OnConflictStrategy.ROLLBACK

interface BaseDao<T> {

    @Insert(onConflict = ROLLBACK)
    fun insert(entity: T) : Long

    @Insert(onConflict = REPLACE)
    fun upsert(entity: T) : Long

    @Delete
    fun delete(entity: T)

}