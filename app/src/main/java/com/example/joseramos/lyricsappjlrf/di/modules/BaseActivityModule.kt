package com.example.joseramos.lyricsappjlrf.di.modules

import android.content.Context
import android.support.v4.app.FragmentManager

import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorImpl
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorManager

import javax.inject.Named

import dagger.Module
import dagger.Provides

//
//const val ACTIVITY_FRAGMENT_MANAGER = "ACTIVITY_FRAGMENT_MANAGER"
//@Named(ACTIVITY_FRAGMENT_MANAGER)

@Module(includes = arrayOf(NavigationModule::class))
class BaseActivityModule {


    @Provides
    @ActivityScope
    internal fun activityFragmentManager(activity: BaseActivity): FragmentManager {
        return activity.supportFragmentManager
    }

//    @ActivityScope
//    @Provides
//    internal fun providesNavigatorManager(fragmentManager: FragmentManager): NavigatorManager {
//        return NavigatorManager(fragmentManager)
//    }
//
//    @ActivityScope
//    @Provides
//    internal fun providesNavigator(navigatorManager: NavigatorManager, context: Context): Navigator {
//        return NavigatorImpl(navigatorManager, context)
//    }
}
