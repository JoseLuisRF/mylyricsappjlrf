package com.example.joseramos.lyricsappjlrf.di.modules

import android.support.v4.app.FragmentManager
import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = arrayOf(NavigationModule::class))
interface ActivitySubComponent : AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseActivity>() {
//
//        @BindsInstance
//        abstract fun fragmentManager(fragmentManager: FragmentManager): Builder
    }


//    @Subcomponent.Builder
//    interface Builder : AndroidInjector.Builder<YourActivity> {
//
//        @BindsInstance
//        fun fragmentManager(fragmentManager: FragmentManager): Builder
//
//        fun build(): ActivitySubComponent
//    }

}