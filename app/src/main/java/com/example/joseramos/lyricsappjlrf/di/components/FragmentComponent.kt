package com.example.joseramos.lyricsappjlrf.di.components


import com.example.joseramos.lyricsappjlrf.di.modules.NetworkModule
import com.example.joseramos.lyricsappjlrf.di.modules.RepositoryModule
import com.example.joseramos.lyricsappjlrf.di.modules.StorageModule
import com.example.joseramos.lyricsappjlrf.di.scopes.FragmentScope
import com.example.joseramos.lyricsappjlrf.presentation.fragments.LyricsFragment
import com.example.joseramos.lyricsappjlrf.presentation.fragments.TopSongsFragment
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment

import dagger.Component

@FragmentScope
@Component(dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(NetworkModule::class,
                RepositoryModule::class,
                StorageModule::class))
interface FragmentComponent {

    fun inject(topSongsFragment: TopSongsFragment)

    fun inject(lyricsFragment: LyricsFragment)

    fun inject(lyricsFragment: BaseFragment)
}
