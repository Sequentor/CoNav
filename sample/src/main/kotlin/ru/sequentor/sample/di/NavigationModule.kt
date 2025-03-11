package ru.sequentor.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sequentor.conav.navigator.Navigator
import ru.sequentor.conav.router.Router
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {

    @Provides
    @Singleton
    fun provideRouter(): Router = Router()

    @Provides
    @Singleton
    fun provideNavigator(router: Router): Navigator = Navigator(router)
}
