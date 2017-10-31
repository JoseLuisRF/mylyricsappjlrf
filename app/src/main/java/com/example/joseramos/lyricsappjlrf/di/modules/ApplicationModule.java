package com.example.joseramos.lyricsappjlrf.di.modules;

import android.content.Context;

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication;
import com.example.joseramos.lyricsappjlrf.UIThread;
import com.example.joseramos.lyricsappjlrf.data.executor.JobExecutor;
import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread;
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private MyLyricsApplication application;

    public ApplicationModule(MyLyricsApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context providesApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
