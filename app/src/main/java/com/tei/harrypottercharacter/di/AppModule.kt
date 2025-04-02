package com.tei.harrypottercharacter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tei.harrypottercharacter.data.local.CharacterDatabase
import com.tei.harrypottercharacter.data.local.CharactersDAO
import com.tei.harrypottercharacter.data.network.APIService
import com.tei.harrypottercharacter.util.BASE_URL
import com.tei.harrypottercharacter.util.DATABASE_NAME
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
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CharacterDatabase {
        return Room.databaseBuilder(app, CharacterDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideIODispatcher(): DispatcherProvider = AppDispatchers()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit) : APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterDAO(appDatabase: CharacterDatabase) : CharactersDAO {
        return appDatabase.charactersDao()
    }

}