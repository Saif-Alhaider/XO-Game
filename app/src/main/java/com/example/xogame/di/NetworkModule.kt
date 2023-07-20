package com.example.xogame.di

import com.example.xogame.data.XORepository
import com.example.xogame.data.XORepositoryImpl
import com.example.xogame.data.local.PlayerDatastore
import com.example.xogame.data.remote.XOSocketService
import com.example.xogame.data.remote.XOSocketServiceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.BODY
            }
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()

            }

        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideXOSocketService(client: HttpClient, gson: Gson): XOSocketService {
        return XOSocketServiceImpl(client, gson)
    }

    @Provides
    @Singleton
    fun providesXORepository(dataStore:PlayerDatastore,xoSocketService: XOSocketService):XORepository{
        return XORepositoryImpl(dataStore,xoSocketService)
    }
}