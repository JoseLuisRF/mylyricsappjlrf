package com.example.joseramos.lyricsappjlrf


import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.joseramos.lyricsappjlrf.di.ComponentsFactory
import com.example.joseramos.lyricsappjlrf.di.components.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyLyricsApplication : Application(), HasActivityInjector {

    private lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
//        AppInjector.init(this)
        appComponent = ComponentsFactory.createApplicationComponent(this)
        appComponent.inject(this)
    }

    companion object {

        @JvmStatic
        fun getAppComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as MyLyricsApplication).appComponent
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
