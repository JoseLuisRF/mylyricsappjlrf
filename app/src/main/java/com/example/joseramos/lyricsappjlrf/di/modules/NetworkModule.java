package com.example.joseramos.lyricsappjlrf.di.modules;


import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi;
import com.example.joseramos.lyricsappjlrf.data.api.datasource.MusicCloudDataSourceImpl;
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource;
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @FragmentScope
    @Provides
    public MusicCloudDataSource providesMusicCloudDataSource(MusicMatchApi api) {
        return new MusicCloudDataSourceImpl(api);
    }
}
