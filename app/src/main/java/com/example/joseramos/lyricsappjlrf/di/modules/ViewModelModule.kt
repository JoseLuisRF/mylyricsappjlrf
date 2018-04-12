package com.example.joseramos.lyricsappjlrf.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope
import com.example.joseramos.lyricsappjlrf.di.scopes.ViewModelKey
import com.example.joseramos.lyricsappjlrf.presentation.viewmodel.ViewModelFactoryEx
import com.example.joseramos.lyricsappjlrf.presentation.viewmodel.TopSongsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(TopSongsViewModel::class)
    abstract fun bindUserViewModel(userViewModel: TopSongsViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryEx): ViewModelProvider.Factory

}