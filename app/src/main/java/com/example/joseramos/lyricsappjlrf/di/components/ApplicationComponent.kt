package com.example.joseramos.lyricsappjlrf.di.components


import android.app.Application
import android.content.Context
import com.example.joseramos.lyricsappjlrf.MyLyricsApplication
import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.database.AppDataBase
import com.example.joseramos.lyricsappjlrf.di.modules.ActivityBindingModule
import com.example.joseramos.lyricsappjlrf.di.modules.ApplicationModule
import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import com.example.joseramos.lyricsappjlrf.presentation.utils.PermissionsManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun deviceUtils(): DeviceUtils

    fun appDataBase(): AppDataBase

    fun permissionsManager(): PermissionsManager

    fun musicMatchApi(): MusicMatchApi

    fun inject(application: MyLyricsApplication)

}
