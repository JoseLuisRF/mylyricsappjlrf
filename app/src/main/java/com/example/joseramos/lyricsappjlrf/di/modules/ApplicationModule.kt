package com.example.joseramos.lyricsappjlrf.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.DiskIOThreadExecutor
import com.example.joseramos.lyricsappjlrf.MainThreadExecutor
import com.example.joseramos.lyricsappjlrf.UIThread
import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.factory.ServiceFactory
import com.example.joseramos.lyricsappjlrf.data.api.interceptors.ApiInterceptor
import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase
import com.example.joseramos.lyricsappjlrf.data.database.DATABASE_NAME
import com.example.joseramos.lyricsappjlrf.data.executor.JobExecutor
import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import com.example.joseramos.lyricsappjlrf.presentation.utils.PermissionsManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @Singleton
    internal fun providesApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    internal fun providesDeviceUtils(application: Application): DeviceUtils {
        return DeviceUtils(application)
    }

    @Provides
    @Singleton
    internal fun providesAppDataBase(application: Application): AppDataBase {
        return Room
                .databaseBuilder(application, AppDataBase::class.java, DATABASE_NAME)
                .build()
    }

    @Provides
    @Singleton
    internal fun providesPermissionsManager(application: Application): PermissionsManager {
        return PermissionsManager(application)
    }


    @Singleton
    @Provides
    fun providesMusicMatchApi(apiInterceptor: ApiInterceptor): MusicMatchApi {
        return ServiceFactory.createRetrofitService(MusicMatchApi::class.java,
                "http://api.musixmatch.com/ws/1.1/",
                apiInterceptor)
    }

    @Provides
    @Singleton
    internal fun providesAppExecutors(appExecutors: AppExecutors): AppExecutors {
        return appExecutors
    }
}

