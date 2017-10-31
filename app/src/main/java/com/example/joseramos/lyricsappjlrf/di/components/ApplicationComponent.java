package com.example.joseramos.lyricsappjlrf.di.components;


import android.content.Context;

import com.example.joseramos.lyricsappjlrf.di.modules.ApplicationModule;
import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread;
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}
