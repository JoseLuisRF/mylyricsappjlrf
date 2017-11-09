package com.example.joseramos.lyricsappjlrf.data.repository.mappers

import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.TrackResponse
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import javax.inject.Inject


class MusicDataMapper @Inject constructor() {

    fun convert(response: TrackResponse) : TrackModel {
        return TrackModel(response.trackId,
                response.trackName,
                response.albumName,
                response.albumId,
                response.artistName,
                response.albumCoverart100x100,
                0)
    }

    fun convert(model: TrackModel) : TopSongsEntity {
        return TopSongsEntity(
                model.trackId,
                model.trackName,
                model.albumName,
                model.albumId,
                model.artistName,
                model.albumImageUrl,
                model.favorite)
    }

    fun convert(entity: TopSongsEntity) : TrackModel {
        return TrackModel(
                entity.trackId,
                entity.trackName,
                entity.albumName,
                entity.albumId,
                entity.artistName,
                entity.albumUrl,
                entity.favorite)
    }

    fun convert(models: List<TrackModel>): List<TopSongsEntity> {
        val entities = mutableListOf<TopSongsEntity>()
        for(model in models ){
            entities.add(convert(model))
        }
        return entities
    }

    fun convertToModels(entities: List<TopSongsEntity>): List<TrackModel>? {
        val models = mutableListOf<TrackModel>()
        for(entity in entities ){
            models.add(convert(entity))
        }
        return models
    }

    fun convert(trackId: Int, response: LyricsResponse): LyricsModel {
        return LyricsModel(
                response.lyricsId,
                trackId,
                response.restricted,
                response.instrumental,
                response.lyricsBody,
                response.lyricsLanguage,
                response.scriptTrackingUrl,
                response.pixelTrackingUrl,
                response.htmlTrackingUrl,
                response.lyricsCopyRight,
                response.updatedTime
        )

    }

    fun convert( model: LyricsModel): SongLyricsEntity {
        return SongLyricsEntity(
                model.lyricsId,
                model.trackId,
                model.restricted,
                model.instrumental,
                model.lyricsBody,
                model.lyricsLanguage,
                model.scriptTrackingUrl,
                model.pixelTrackingUrl,
                model.htmlTrackingUrl,
                model.lyricsCopyRight,
                model.updatedTime
        )
    }

    fun convert(entity: SongLyricsEntity): LyricsModel {
        return LyricsModel(
                entity.lyricsId,
                entity.trackId,
                entity.restricted,
                entity.instrumental,
                entity.lyricsBody,
                entity.lyricsLanguage,
                entity.scriptTrackingUrl,
                entity.pixelTrackingUrl,
                entity.htmlTrackingUrl,
                entity.lyricsCopyRight,
                entity.updatedTime
        )
    }

}