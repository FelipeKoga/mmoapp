package com.koga.mmoapp.di

import android.content.Context
import androidx.room.Room
import com.koga.mmoapp.data.local.MMODatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext applicationContext: Context
    ): MMODatabase {
        return Room
            .databaseBuilder(
                applicationContext,
                MMODatabase::class.java,
                MMODatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesGameDao(
        database: MMODatabase
    ) = database.gameDao
}