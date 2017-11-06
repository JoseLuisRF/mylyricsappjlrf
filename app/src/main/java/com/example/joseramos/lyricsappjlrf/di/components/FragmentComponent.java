package com.example.joseramos.lyricsappjlrf.di.components;


import com.example.joseramos.lyricsappjlrf.di.modules.NetworkModule;
import com.example.joseramos.lyricsappjlrf.di.modules.RepositoryModule;
import com.example.joseramos.lyricsappjlrf.di.modules.StorageModule;
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope;
import com.example.joseramos.lyricsappjlrf.presentation.fragments.LyricsFragment;
import com.example.joseramos.lyricsappjlrf.presentation.fragments.TopSongsFragment;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@FragmentScope
@Component(dependencies = {ApplicationComponent.class},
        modules = {
                NetworkModule.class,
                RepositoryModule.class,
                StorageModule.class})
public interface FragmentComponent {

    void inject(TopSongsFragment topSongsFragment);

    void inject(LyricsFragment lyricsFragment);
}
