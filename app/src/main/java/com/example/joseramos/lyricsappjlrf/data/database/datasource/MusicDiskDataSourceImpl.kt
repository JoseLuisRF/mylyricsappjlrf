package com.example.joseramos.lyricsappjlrf.data.database.datasource

import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class MusicDiskDataSourceImpl @Inject constructor(private val database: AppDataBase) : MusicDiskDataSource {

    override fun insertTopSong(entity: TopSongsEntity): Flowable<Long> {
        return Flowable.create({ e ->
            e.onNext(database.topSongsDao().insert(entity))
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun insertTopSong(entity: List<TopSongsEntity>): Flowable<List<Long>> {
        return Flowable.create({ e ->
            database.topSongsDao().insertAll(entity)
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun selectTopSongs(): Flowable<List<TopSongsEntity>> {
        return Flowable.create({e ->
            e.onNext(database.topSongsDao().selectAll())
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun insertLyrics(entity: SongLyricsEntity): Flowable<Int> {
        return Flowable.create({e ->
            val res = database.lyricsDao().upsert(entity)
            if( res > 0 ){
                e.onNext(entity.lyricsId)
            } else {
                e.tryOnError(Throwable("There was an error inserting SongLyricsEntity"))
            }
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun selectLyrics(trackId: Int): Flowable<SongLyricsEntity> {
        return Flowable.create({e ->
            e.onNext(database.lyricsDao().selectLyrics(trackId))
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    override fun selectSong(trackId: Int): Flowable<TopSongsEntity> {
        return Flowable.create({e ->
            e.onNext(database.topSongsDao().selectSongById(trackId))
            e.onComplete()
        }, BackpressureStrategy.BUFFER)
    }
}