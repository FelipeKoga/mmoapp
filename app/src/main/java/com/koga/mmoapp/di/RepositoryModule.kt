package com.koga.mmoapp.di

import com.koga.mmoapp.data.repository.GameRepository
import com.koga.mmoapp.data.repository.impl.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun providesGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository
}