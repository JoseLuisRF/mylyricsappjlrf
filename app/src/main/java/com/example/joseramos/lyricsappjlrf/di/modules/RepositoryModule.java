package com.example.joseramos.lyricsappjlrf.di.modules;


import com.example.joseramos.lyricsappjlrf.data.repository.MusicRepositoryImpl;
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope;
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @FragmentScope
    @Provides
    public MusicRepository providesMusicRepository(MusicRepositoryImpl repository) {
        return repository;
    }
}
