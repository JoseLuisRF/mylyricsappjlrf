package com.example.joseramos.lyricsappjlrf.di.modules

import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase
import com.example.joseramos.lyricsappjlrf.data.database.datasource.MusicDiskDataSourceImpl
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class StorageModule @Inject constructor() {

    @FragmentScope
    @Provides
    fun providesMusicDiskDataSource (dataBase: AppDataBase) : MusicDiskDataSource {
        return MusicDiskDataSourceImpl(dataBase)
    }

}