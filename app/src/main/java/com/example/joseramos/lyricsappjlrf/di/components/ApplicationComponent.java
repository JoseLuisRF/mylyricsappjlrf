package com.example.joseramos.lyricsappjlrf.di.components;


import android.content.Context;

import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase;
import com.example.joseramos.lyricsappjlrf.di.modules.ApplicationModule;
import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread;
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor;
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    DeviceUtils deviceUtils();

    AppDataBase appDataBase();
}
