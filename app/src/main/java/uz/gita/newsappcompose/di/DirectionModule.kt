package uz.gita.newsappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.newsappcompose.presentation.screen.home.HomeDirection
import uz.gita.newsappcompose.presentation.screen.home.HomeDirectionImpl
import uz.gita.newsappcompose.presentation.screen.readnews.ReadDispatcher
import uz.gita.newsappcompose.presentation.screen.readnews.ReadDispatcherImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @Binds
    fun bindSplashDirection(impl:HomeDirectionImpl): HomeDirection

    @Binds
    fun bindLoginDirection(impl: ReadDispatcherImpl):ReadDispatcher


}