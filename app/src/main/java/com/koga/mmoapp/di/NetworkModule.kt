package com.koga.mmoapp.di

import com.koga.mmoapp.BuildConfig
import com.koga.mmoapp.data.network.GameService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://www.mmobomb.com/api1/"

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        logging.setLevel(level)

        return OkHttpClient()
            .newBuilder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }

}