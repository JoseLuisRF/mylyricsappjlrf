package com.example.joseramos.lyricsappjlrf.di;

import android.content.Context;

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication;
import com.example.joseramos.lyricsappjlrf.di.components.ApplicationComponent;
import com.example.joseramos.lyricsappjlrf.di.components.DaggerApplicationComponent;
import com.example.joseramos.lyricsappjlrf.di.components.DaggerFragmentComponent;
import com.example.joseramos.lyricsappjlrf.di.components.FragmentComponent;
import com.example.joseramos.lyricsappjlrf.di.modules.NetworkModule;
import com.example.joseramos.lyricsappjlrf.di.modules.RepositoryModule;
import com.example.joseramos.lyricsappjlrf.di.modules.StorageModule;


public class ComponentsFactory {

    public static ApplicationComponent createApplicationComponent(Context context) {
        return DaggerApplicationComponent.builder()
                .application((MyLyricsApplication) context)
                .build();
    }

    public static FragmentComponent createUserComponent(ApplicationComponent applicationComponent) {
        return DaggerFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule())
                .storageModule(new StorageModule())
                .build();
    }
}
