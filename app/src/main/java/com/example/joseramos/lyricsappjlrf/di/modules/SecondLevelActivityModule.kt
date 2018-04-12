package com.example.joseramos.lyricsappjlrf.di.modules

import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope
import com.example.joseramos.lyricsappjlrf.presentation.activities.SecondLevelActivity
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import dagger.Binds
import dagger.Module

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class SecondLevelActivityModule {

    @ActivityScope
    @Binds
    internal abstract fun baseActivity(mainActivity: SecondLevelActivity): BaseActivity
}
