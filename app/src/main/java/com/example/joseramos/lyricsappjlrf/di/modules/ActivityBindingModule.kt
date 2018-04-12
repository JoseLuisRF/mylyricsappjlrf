package com.example.joseramos.lyricsappjlrf.di.modules

import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope
import com.example.joseramos.lyricsappjlrf.presentation.activities.MainActivity
import com.example.joseramos.lyricsappjlrf.presentation.activities.SecondLevelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


//@Module(subcomponents = arrayOf(ActivitySubComponent::class))
@Module
abstract class ActivityBindingModule {

//    @Binds
//    @IntoMap
//    @ActivityKey(BaseActivity::class)
//    abstract fun bindYourActivityInjectorFactory(builder: ActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>


//    @ContributesAndroidInjector(modules = arrayOf(NavigationModule::class))
//    internal abstract fun contributeBaseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun contributeMainActivity(): MainActivity
//

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SecondLevelActivityModule::class))
    internal abstract fun contributeSecondLevelActivity(): SecondLevelActivity

}


