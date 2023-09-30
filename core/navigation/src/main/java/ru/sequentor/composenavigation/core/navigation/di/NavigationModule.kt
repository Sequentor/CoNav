package ru.sequentor.composenavigation.core.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sequentor.composenavigation.core.navigation.router.ChannelRouter
import ru.sequentor.composenavigation.core.navigation.router.Router
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {

    @Provides
    @Singleton
    fun provideRouter(): Router = ChannelRouter()
}
