package com.example.joseramos.lyricsappjlrf.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope
import com.example.joseramos.lyricsappjlrf.di.scopes.ViewModelKey
import com.example.joseramos.lyricsappjlrf.presentation.viewmodel.LyricsViewModel
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
    abstract fun bindTopSongsViewModel(viewModel: TopSongsViewModel): ViewModel


    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(LyricsViewModel::class)
    abstract fun bindLyricsViewModel(viewModel: LyricsViewModel): ViewModel

    @FragmentScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryEx): ViewModelProvider.Factory


}