package com.example.xogame.di

import android.content.Context
import com.example.xogame.data.local.PlayerDatastore
import com.example.xogame.data.local.PlayerDatastoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePlayerDataStore(@ApplicationContext context: Context): PlayerDatastore {
        return PlayerDatastoreImpl(context)
    }
}